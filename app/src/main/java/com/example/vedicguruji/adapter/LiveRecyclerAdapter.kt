package com.example.vedicguruji.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vedicguruji.R
import com.example.vedicguruji.model.Comment
import com.example.vedicguruji.model.SlideItem
import com.squareup.picasso.Picasso
import java.util.ArrayList

class LiveRecyclerAdapter(

    val context : Context,
    val liveList : ArrayList<SlideItem>
) : RecyclerView.Adapter<LiveRecyclerAdapter.LiveViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):LiveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container,parent,false)
        return LiveViewHolder(view)

    }

    override fun onBindViewHolder(holder: LiveViewHolder, position: Int) {

        val live = liveList[position]
        Picasso.get().load(live.icon).error(R.drawable.app_logo).into(holder.imageSlideIcon)
        holder.slideImageTitle.text = live.title

















    }

    override fun getItemCount(): Int {
        return liveList.size
    }
    class LiveViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val imageSlideIcon : ImageView = view.findViewById(R.id.imageSlideIcon)
        val slideImageTitle : TextView = view.findViewById(R.id.slideImageTitle)


    }




}