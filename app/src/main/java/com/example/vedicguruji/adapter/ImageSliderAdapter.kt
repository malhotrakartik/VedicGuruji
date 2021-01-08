package com.example.vedicguruji.adapter

import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vedicguruji.R
import com.example.vedicguruji.model.SlideItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slide_item_container.view.*
import org.w3c.dom.Text

class ImageSliderAdapter(private val slideItem: List<SlideItem>) : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {
    inner class ImageSliderViewHolder(view : View) :RecyclerView.ViewHolder(view){
        private val imageSlideIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)
        private val slideImageTitle = view.findViewById<TextView>(R.id.slideImageTitle)

        fun bind(slideItem : SlideItem){
            slideImageTitle.text = slideItem.title
            Picasso.get().load(slideItem.icon).error(R.drawable.app_logo).into(imageSlideIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        return ImageSliderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return slideItem.size
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.bind(slideItem[position])
    }
}