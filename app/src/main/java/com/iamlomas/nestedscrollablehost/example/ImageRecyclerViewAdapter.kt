package com.iamlomas.nestedscrollablehost.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageRecyclerViewAdapter(
    private val images: List<Int>,
    @RecyclerView.Orientation private val orientation: Int
) : RecyclerView.Adapter<ImageRecyclerViewAdapter.ImageRecyclerViewViewHolder>() {

    inner class ImageRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageRecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                if (orientation == RecyclerView.HORIZONTAL) {
                    R.layout.horizontal_image_itemview
                } else {
                    R.layout.vertical_image_itemview
                },
                parent,
                false
            )
        return ImageRecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageRecyclerViewViewHolder, position: Int) {
        val currentImage = images[position]

        if (orientation == RecyclerView.HORIZONTAL) {
            holder.itemView.findViewById<ImageView>(R.id.ivHorizontal)
                .setImageResource(currentImage)
        } else {
            holder.itemView.findViewById<ImageView>(R.id.ivVertical).setImageResource(currentImage)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}
