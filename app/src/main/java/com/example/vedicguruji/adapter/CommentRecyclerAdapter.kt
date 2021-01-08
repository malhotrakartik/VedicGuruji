package com.example.vedicguruji.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vedicguruji.R
import com.example.vedicguruji.activity.DescriptionActivity
import com.example.vedicguruji.activity.OtpLoginActivity
import com.example.vedicguruji.model.Comment
import com.example.vedicguruji.model.Expert
import com.squareup.picasso.Picasso
import java.util.*

class CommentRecyclerAdapter(

    val context : Context,
    val commentList : ArrayList<Comment>
) : RecyclerView.Adapter<CommentRecyclerAdapter.CommentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_comment_single_row,parent,false)
        return CommentViewHolder(view)

    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {

        val comment = commentList[position]
        holder.nameComment.text = comment.name
        holder.commentRating.text = comment.comment_rating
        holder.txtComment.text = comment.comment
















    }

    override fun getItemCount(): Int {
        return commentList.size
    }
    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val nameComment : TextView = view.findViewById(R.id.nameComment)
        val commentRating : TextView = view.findViewById(R.id.commentRating)
        val txtComment : TextView = view.findViewById(R.id.txtComment)


    }




}