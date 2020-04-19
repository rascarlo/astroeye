package com.rascarlo.astroeye.recents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rascarlo.astroeye.databinding.RecentImageItemBinding
import com.rascarlo.astroeye.network.RecentImage
import timber.log.Timber

/**
 * list adapter for [RecentImagesFragment]
 */
class RecentImagesAdapter :
    ListAdapter<RecentImage, RecentImagesAdapter.RecentImageViewHolder>(DiffCallBack) {

    /**
     * diff util callback for [RecentImage]
     * wrapped into a companion object
     * use object to compare if [areItemsTheSame]
     * use id to compare if [areContentsTheSame]
     */
    companion object DiffCallBack : DiffUtil.ItemCallback<RecentImage>() {
        override fun areItemsTheSame(oldItem: RecentImage, newItem: RecentImage): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RecentImage, newItem: RecentImage): Boolean {
            return oldItem.id == newItem.id
        }
    }

    // view holder
    class RecentImageViewHolder(private var binding: RecentImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recentImage: RecentImage) {
            binding.recentImage = recentImage
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecentImageViewHolder(RecentImageItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecentImageViewHolder, position: Int) {
        val recentImages = getItem(position)
        Timber.e(recentImages.title)
        holder.bind(recentImages)
    }
}