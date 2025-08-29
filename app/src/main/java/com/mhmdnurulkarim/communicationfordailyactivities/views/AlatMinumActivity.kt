package com.mhmdnurulkarim.communicationfordailyactivities.views

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mhmdnurulkarim.communicationfordailyactivities.R
import com.mhmdnurulkarim.communicationfordailyactivities.databinding.ActivityAlatMinumBinding
import kotlin.math.sqrt

class AlatMinumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlatMinumBinding
    private lateinit var mediaPlayer: MediaPlayer

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlatMinumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.alat_gelas)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            ivGelas.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val x = motionEvent.rawX - view.width / 2
                        val y = motionEvent.rawY - view.height / 2
                        view.x = x
                        view.y = y
                    }

                    MotionEvent.ACTION_UP -> {
                        if (gelas()) {
                            mediaPlayer.start()
                        }
                    }
                }
                true
            }
            ivBack.setOnClickListener {
                onBackPressed()
                finish()
            }
        }
    }

    private fun gelas(): Boolean {
        val gelasX = binding.ivGelas.x + binding.ivGelas.width / 2
        val gelasY = binding.ivGelas.y + binding.ivGelas.height / 2
        val jawabanX = binding.ivJawaban.x + binding.ivJawaban.width / 2
        val jawabanY = binding.ivJawaban.y + binding.ivJawaban.height / 2

        val distance =
            sqrt(((gelasX - jawabanX) * (gelasX - jawabanX) + (gelasY - jawabanY) * (gelasY - jawabanY)).toDouble())
        val circleRadius = binding.ivJawaban.width / 2

        return distance <= circleRadius
    }
}