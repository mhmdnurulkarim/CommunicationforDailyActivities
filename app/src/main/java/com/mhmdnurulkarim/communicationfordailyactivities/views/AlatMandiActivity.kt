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
import com.mhmdnurulkarim.communicationfordailyactivities.databinding.ActivityAlatMandiBinding
import kotlin.math.sqrt

class AlatMandiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlatMandiBinding
    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlatMandiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            ivSabun.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val x = motionEvent.rawX - view.width / 2
                        val y = motionEvent.rawY - view.height / 2
                        view.x = x
                        view.y = y
                    }

                    MotionEvent.ACTION_UP -> {
                        if (sabun()) {
                            mediaPlayer =
                                MediaPlayer.create(this@AlatMandiActivity, R.raw.alat_sabun)
                            mediaPlayer?.start()
                        }
                    }
                }
                true
            }
            ivGayung.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val x = motionEvent.rawX - view.width / 2
                        val y = motionEvent.rawY - view.height / 2
                        view.x = x
                        view.y = y
                    }

                    MotionEvent.ACTION_UP -> {
                        if (gayung()) {
                            mediaPlayer =
                                MediaPlayer.create(this@AlatMandiActivity, R.raw.alat_gayung)
                            mediaPlayer?.start()
                        }
                    }
                }
                true
            }
            ivHanduk.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val x = motionEvent.rawX - view.width / 2
                        val y = motionEvent.rawY - view.height / 2
                        view.x = x
                        view.y = y
                    }

                    MotionEvent.ACTION_UP -> {
                        if (handuk()) {
                            mediaPlayer =
                                MediaPlayer.create(this@AlatMandiActivity, R.raw.alat_handuk)
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

    private fun sabun(): Boolean {
        val sabunX = binding.ivSabun.x + binding.ivSabun.width / 2
        val sabunY = binding.ivSabun.y + binding.ivSabun.height / 2
        val jawabanX = binding.ivJawaban.x + binding.ivJawaban.width / 2
        val jawabanY = binding.ivJawaban.y + binding.ivJawaban.height / 2

        val distance =
            sqrt(((sabunX - jawabanX) * (sabunX - jawabanX) + (sabunY - jawabanY) * (sabunY - jawabanY)).toDouble())
        val circleRadius = binding.ivJawaban.width / 2

        return distance <= circleRadius
    }

    private fun gayung(): Boolean {
        val gayungX = binding.ivGayung.x + binding.ivGayung.width / 2
        val gayungY = binding.ivGayung.y + binding.ivGayung.height / 2
        val jawabanX = binding.ivJawaban.x + binding.ivJawaban.width / 2
        val jawabanY = binding.ivJawaban.y + binding.ivJawaban.height / 2

        val distance =
            sqrt(((gayungX - jawabanX) * (gayungX - jawabanX) + (gayungY - jawabanY) * (gayungY - jawabanY)).toDouble())
        val circleRadius = binding.ivJawaban.width / 2

        return distance <= circleRadius
    }

    private fun handuk(): Boolean {
        val handukX = binding.ivHanduk.x + binding.ivHanduk.width / 2
        val handukY = binding.ivHanduk.y + binding.ivHanduk.height / 2
        val jawabanX = binding.ivJawaban.x + binding.ivJawaban.width / 2
        val jawabanY = binding.ivJawaban.y + binding.ivJawaban.height / 2

        val distance =
            sqrt(((handukX - jawabanX) * (handukX - jawabanX) + (handukY - jawabanY) * (handukY - jawabanY)).toDouble())
        val circleRadius = binding.ivJawaban.width / 2

        return distance <= circleRadius
    }
}