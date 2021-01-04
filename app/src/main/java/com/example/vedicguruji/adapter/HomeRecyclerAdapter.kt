package com.example.vedicguruji.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vedicguruji.R
import com.example.vedicguruji.activity.DescriptionActivity
import com.example.vedicguruji.model.Expert
import com.squareup.picasso.Picasso
import java.util.*

class HomeRecyclerAdapter(

    val context :Context,
val itemList : ArrayList<Expert>
) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_home_single_row,parent,false)
        return HomeViewHolder(view)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val expert = itemList[position]
        holder.nameExpert.text = expert.expert_name
        val exp : String = expert.expert_experience
        val rate : String = expert.expert_rate_per_min
        val status: String = expert.expert_work_status.toUpperCase(Locale.ROOT)
        val img = expert.expert_img
        var finalImg:String = ""





        finalImg   = img.substring(0,7) + img.substring(7,16) + img.substring(16)


        println(finalImg)



        holder.expExpert.text = "Experience : $exp Years"
        holder.rateExpert.text = "Rate Per Minute : Rs.$rate"
        holder.expertStatus.text = "STATUS : $status"
        val image : String = "https://vedicguruji.com/$finalImg"




        Picasso.get().load(image).error(R.drawable.people_icon).into(holder.imgExpert)

        holder.llContainer.setOnClickListener {



            val intent = Intent(context , DescriptionActivity::class.java)
            intent.putExtra("exp_name" , expert.expert_name)
            intent.putExtra("exp_desc",expert.expert_description)
            intent.putExtra("exp_image",image)
            intent.putExtra("exp_rate",expert.expert_rate_per_min)
            intent.putExtra("exp_status",expert.expert_work_status)
            intent.putExtra("exp_experience",expert.expert_experience)
            context.startActivity(intent)


        }


    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val imgExpert : ImageView = view.findViewById(R.id.imgExpert)
        val nameExpert : TextView = view.findViewById(R.id.nameExpert)
        val expExpert : TextView = view.findViewById(R.id.expExpert)
        val expertStatus : TextView = view.findViewById(R.id.expertStatus)
        val rateExpert : TextView = view.findViewById(R.id.rateExpert)
        val llContainer : LinearLayout = view.findViewById(R.id.llContainer)


    }

}

