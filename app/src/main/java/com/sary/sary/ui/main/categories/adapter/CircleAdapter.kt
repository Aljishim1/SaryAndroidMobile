package com.sary.sary.ui.main.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sary.sary.R
import com.sary.sary.data.models.entity.CatalogData
import com.squareup.picasso.Picasso

internal class CircleAdapter(private var data: List<CatalogData>) :
    RecyclerView.Adapter<CircleAdapter.ViewHolder>() {
    private val picasso = Picasso.get()

    internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageCircle)
        val text: TextView = view.findViewById(R.id.textCircle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.circle_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = data[position].name
        picasso.load(data[position].image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}