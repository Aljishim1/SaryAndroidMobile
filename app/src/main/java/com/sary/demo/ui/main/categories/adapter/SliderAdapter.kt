package com.sary.demo.ui.main.categories.adapter

import android.content.Context
import com.smarteist.autoimageslider.SliderViewAdapter
import android.widget.Toast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.sary.demo.R
import com.sary.demo.data.models.entity.Banner
import com.squareup.picasso.Picasso


class SliderAdapter(private val context: Context, private var data: List<Banner>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private val picasso = Picasso.get()

    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        var image: ImageView
        init {
            image = itemView.findViewById(R.id.sliderImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        picasso.load(data[position].image).into(viewHolder.image)
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(context, data[position].link, Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getCount(): Int {
        return data.size
    }
}
