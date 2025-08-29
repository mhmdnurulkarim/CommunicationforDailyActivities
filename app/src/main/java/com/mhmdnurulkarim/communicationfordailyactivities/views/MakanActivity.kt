package com.mhmdnurulkarim.communicationfordailyactivities.views

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mhmdnurulkarim.communicationfordailyactivities.R
import com.mhmdnurulkarim.communicationfordailyactivities.databinding.ActivityMakanBinding
import com.mhmdnurulkarim.communicationfordailyactivities.model.Item

class MakanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakanBinding
    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataItem = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, Item::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if (dataItem != null) {
            mediaPlayer = MediaPlayer.create(this, dataItem.audio)
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Glide.with(this)
            .load(dataItem?.photo)
            .centerCrop()
            .apply(RequestOptions().transform(RoundedCorners(16)))
            .into(binding.ivActivity)
        binding.tvActivity.text = dataItem?.activitiesName

        binding.apply {
            cvAktivitasMakan.setOnClickListener {
                mediaPlayer.start()
            }
            ivBack.setOnClickListener {
                mediaPlayer.release()
                onBackPressed()
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "EXTRA DATA"
    }
}