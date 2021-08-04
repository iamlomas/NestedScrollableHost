package com.iamlomas.nestedscrollablehost.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iamlomas.nestedscrollablehost_sample.Shinobi

class ListRecyclerViewAdapter(
    private val shinobiList: List<Shinobi>
) : RecyclerView.Adapter<ListRecyclerViewAdapter.ListRecyclerViewViewHolder>() {

    inner class ListRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_list_itemview, parent, false)
        return ListRecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListRecyclerViewViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvName).text = shinobiList[position].name
            findViewById<CheckBox>(R.id.cbChecked).isChecked = shinobiList[position].isChecked
        }
    }

    override fun getItemCount(): Int {
        return shinobiList.size
    }
}