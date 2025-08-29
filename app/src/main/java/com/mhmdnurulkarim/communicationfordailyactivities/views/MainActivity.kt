package com.mhmdnurulkarim.communicationfordailyactivities.views

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmdnurulkarim.communicationfordailyactivities.R
import com.mhmdnurulkarim.communicationfordailyactivities.adapter.ItemListAdapter
import com.mhmdnurulkarim.communicationfordailyactivities.databinding.ActivityMainBinding
import com.mhmdnurulkarim.communicationfordailyactivities.model.Item
import com.mhmdnurulkarim.communicationfordailyactivities.utils.ListItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: ItemListAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //RecyclerView
        mAdapter = ItemListAdapter()
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            mAdapter.submitList(ListItem.mainActivitiesWithGame)
        } else {
            mLayoutManager = GridLayoutManager(this, 2)
            mAdapter.submitList(ListItem.mainActivitiesNoGame)
        }

        binding.rvList.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        mAdapter.setOnItemClickCallback(object : ItemListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Item) {
                if (data.activitiesName == "Game") {
                    startActivity(Intent(this@MainActivity, GameActivity::class.java))
                } else {
                    val intentToDetail = Intent(this@MainActivity, MakanActivity::class.java)
                    intentToDetail.putExtra(MakanActivity.EXTRA_DATA, data)
                    startActivity(intentToDetail)
                }
            }
        })

        binding.ivInformation.setOnClickListener {
            startActivity(Intent(this@MainActivity, InformationActivity::class.java))
        }
        binding.cvMainGame?.setOnClickListener {
            startActivity(Intent(this@MainActivity, GameActivity::class.java))
        }
    }
}