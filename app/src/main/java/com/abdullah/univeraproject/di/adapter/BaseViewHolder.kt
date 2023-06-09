package com.abdullah.univeraproject.di.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T> internal constructor(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T,itemClickListener:(T)->Unit,context: Context?)
}