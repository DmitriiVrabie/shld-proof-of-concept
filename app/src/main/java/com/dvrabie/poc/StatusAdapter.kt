package com.dvrabie.poc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_status.view.*

class StatusAdapter(
    val items: MutableList<String>
) : RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        return StatusViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_status, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.onBing(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: String) {
        items.add(item)
        notifyItemInserted(items.size)
    }

    fun updateList(newList: List<String>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    class StatusViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun onBing(item: String) {
            itemView.tvStatus.text = item
        }
    }

}