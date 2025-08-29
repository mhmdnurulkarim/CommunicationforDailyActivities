package com.mhmdnurulkarim.communicationfordailyactivities.views

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mhmdnurulkarim.communicationfordailyactivities.R
import com.mhmdnurulkarim.communicationfordailyactivities.databinding.ActivityAlatMakanBinding
import kotlin.math.sqrt

class AlatMakanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlatMakanBinding
    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlatMakanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            ivPiring.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val x = motionEvent.rawX - view.width / 2
                        val y = motionEvent.rawY - view.height / 2
                        view.x = x
                        view.y = y
                    }

                    MotionEvent.ACTION_UP -> {
                        if (piring()) {
                            mediaPlayer =
                                MediaPlayer.create(this@AlatMakanActivity, R.raw.alat_piring)
                            mediaPlayer?.start()
                        }
                    }
                }
                true
            }
            ivSendok.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val x = motionEvent.rawX - view.width / 2
                        val y = motionEvent.rawY - view.height / 2
                        view.x = x
                        view.y = y
                    }

                    MotionEvent.ACTION_UP -> {
                        if (sendokGarpu()) {
                            mediaPlayer =
                                MediaPlayer.create(this@AlatMakanActivity, R.raw.alat_sendok_garpu)
                            mediaPlayer?.start()
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

    private fun piring(): Boolean {
        val piringX = binding.ivPiring.x + binding.ivPiring.width / 2
        val piringY = binding.ivPiring.y + binding.ivPiring.height / 2
        val jawabanX = binding.ivJawaban.x + binding.ivJawaban.width / 2
        val jawabanY = binding.ivJawaban.y + binding.ivJawaban.height / 2

        val distance =
            sqrt(((piringX - jawabanX) * (piringX - jawabanX) + (piringY - jawabanY) * (piringY - jawabanY)).toDouble())
        val circleRadius = binding.ivJawaban.width / 2

        return distance <= circleRadius
    }

    private fun sendokGarpu(): Boolean {
        val sendokGarpuX = binding.ivSendok.x + binding.ivSendok.width / 2
        val sendokGarpuY = binding.ivSendok.y + binding.ivSendok.height / 2
        val jawabanX = binding.ivJawaban.x + binding.ivJawaban.width / 2
        val jawabanY = binding.ivJawaban.y + binding.ivJawaban.height / 2

        val distance =
            sqrt(((sendokGarpuX - jawabanX) * (sendokGarpuX - jawabanX) + (sendokGarpuY - jawabanY) * (sendokGarpuY - jawabanY)).toDouble())
        val circleRadius = binding.ivJawaban.width / 2

        return distance <= circleRadius
    }
}