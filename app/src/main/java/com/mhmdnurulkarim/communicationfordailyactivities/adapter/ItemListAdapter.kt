package com.mhmdnurulkarim.communicationfordailyactivities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mhmdnurulkarim.communicationfordailyactivities.databinding.ItemBinding
import com.mhmdnurulkarim.communicationfordailyactivities.model.Item

class ItemListAdapter : ListAdapter<Item, ItemListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataPosition = getItem(position)
        holder.bind(dataPosition)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(dataPosition)
        }
    }

    inner class ListViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.photo)
                    .centerCrop()
                    .apply(RequestOptions().transform(RoundedCorners(16)))
                    .into(ivItemCard)
                tvItemCard.text = item.activitiesName
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Item)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.activitiesName == newItem.activitiesName
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}