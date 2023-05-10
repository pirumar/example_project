package com.abdullah.univeraproject.viewholder

import android.content.Context
import com.abdullah.univeraproject.databinding.AlbumItemBinding
import com.abdullah.univeraproject.di.adapter.BaseViewHolder
import com.abdullah.univeraproject.models.Album

class AlbumViewHolder(private val itemBinding: AlbumItemBinding) :
    BaseViewHolder<Album>(itemBinding.root) {


    override fun bind(item: Album, itemClickListener: (Album) -> Unit, context: Context?) {
        apply {

            itemBinding.tvTitle.text = item.title
            itemView.setOnClickListener {
                itemClickListener(item)
            }
        }
    }
}