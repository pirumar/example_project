package com.abdullah.univeraproject.viewholder

import android.content.Context
import com.abdullah.univeraproject.databinding.PhotoItemBinding
import com.abdullah.univeraproject.di.adapter.BaseViewHolder
import com.abdullah.univeraproject.models.Photo
import com.bumptech.glide.Glide

class PhotoViewHolder(private val itemBinding: PhotoItemBinding) :
    BaseViewHolder<Photo>(itemBinding.root) {


    override fun bind(item: Photo, itemClickListener: (Photo) -> Unit, context: Context?) {
        if (context != null) {
            Glide.with(context.applicationContext).load(item.url).into(itemBinding.imgView)
        }
        itemBinding.tvTitle.text = item.title
        itemView.setOnClickListener {
            itemClickListener(item)
        }
    }
}