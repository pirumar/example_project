package com.abdullah.univeraproject.viewholder

import android.content.Context
import com.abdullah.univeraproject.databinding.CommentItemBinding
import com.abdullah.univeraproject.di.adapter.BaseViewHolder
import com.abdullah.univeraproject.models.Comment

class CommentViewHolder(private val itemBinding: CommentItemBinding) :
    BaseViewHolder<Comment>(itemBinding.root) {


    override fun bind(item: Comment, itemClickListener: (Comment) -> Unit, context: Context?) {
        itemBinding.tvComment.text = item.body
        itemBinding.tvEmail.text = item.Email
        itemBinding.tvUser.text = item.name
    }
}