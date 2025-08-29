package com.mhmdnurulkarim.communicationfordailyactivities.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mhmdnurulkarim.communicationfordailyactivities.R
import com.mhmdnurulkarim.communicationfordailyactivities.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        alatMakan()
        alatMinum()
        alatMandi()

        binding.ivBack.setOnClickListener {
            onBackPressed()
            finish()
        }

    }

    fun alatMakan() {
        binding.cvAlatMakan.setOnClickListener {
            startActivity(Intent(this@GameActivity, AlatMakanActivity::class.java))
        }
    }

    fun alatMinum() {
        binding.cvAlatMinum.setOnClickListener {
            startActivity(Intent(this@GameActivity, AlatMinumActivity::class.java))
        }
    }

    fun alatMandi() {
        binding.cvAlatMandi.setOnClickListener {
            startActivity(Intent(this@GameActivity, AlatMandiActivity::class.java))
        }
    }
}