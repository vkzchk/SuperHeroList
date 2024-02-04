package com.example.superherolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(val items: MutableList<SuperHeroTransformResponse> = mutableListOf(), val onClick:(String) ->Unit):RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val listItem:View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false)
        return RecyclerViewHolder(listItem)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        with(holder) {
            title.text = items[position].name
            work.text = items[position].work
            Glide.with(itemView.context)
                .load(items[position].image)
                .into(image)
            itemView.setOnClickListener { onClick(items[position].name) }
        }
    }
}

class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.itemTitle)
    val image: ImageView = itemView.findViewById(R.id.itemImage)
    val work: TextView = itemView.findViewById(R.id.itemWork)
}