package com.sary.demo.ui.main.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sary.demo.R
import com.sary.demo.data.models.entity.CatalogData
import com.squareup.picasso.Picasso

internal class TopProductAdapter(private var data: List<CatalogData>) :
    RecyclerView.Adapter<TopProductAdapter.ViewHolder>() {
    private val picasso = Picasso.get()

    internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.topProductImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_product_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        picasso.load(data[position].image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}