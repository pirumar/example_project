package com.abdullah.univeraproject.di.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

typealias CreateViewHolder<T> = (viewGroup: ViewGroup) -> BaseViewHolder<T>

class RecAdapter<T>(
    private val _items: List<T>?,
    private val _context: Context?,
    private val _baseViewHolderCreate: CreateViewHolder<T>,
    private val itemClickListener: (T) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return _baseViewHolderCreate(
            parent
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(_items!![position], itemClickListener, _context)
    }

    override fun getItemCount() = _items?.size ?: 0
    override fun getItemViewType(position: Int): Int {
        return 0
    }


    fun setItems(items: List<T>) {
        (_items as ArrayList<T>).addAll(items)
        notifyDataSetChanged()
    }
}