package com.example.vedicguruji.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.vedicguruji.R
import com.example.vedicguruji.adapter.CommentRecyclerAdapter
import com.example.vedicguruji.model.Comment
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException

class DescriptionActivity : AppCompatActivity() {
    lateinit var toolbar : androidx.appcompat.widget.Toolbar
    lateinit var expImageDesc : ImageView
    lateinit var expNameDesc : TextView
    lateinit var ratingDesc : TextView
    lateinit var statusDesc : TextView
    lateinit var experienceDesc : TextView
    lateinit var pricing : TextView
    lateinit var maindesc : TextView
    val commentInfoList = arrayListOf<Comment>()

    lateinit var recyclerComment: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerAdapter: CommentRecyclerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        recyclerComment = findViewById(R.id.recyclerComment)


        toolbar = findViewById(R.id.toolbar)
        expImageDesc = findViewById(R.id.expImageDesc)
        expNameDesc = findViewById(R.id.expNameDesc)
        ratingDesc = findViewById(R.id.ratingDesc)
        statusDesc = findViewById(R.id.statusDesc)
        experienceDesc = findViewById(R.id.experienceDesc)
        pricing = findViewById(R.id.pricing)
        maindesc = findViewById(R.id.maindesc)



        val position = intent.getStringExtra("position")
        StartDescriptionActivity(position)
    }

    fun StartDescriptionActivity(position : String){
        val queue = Volley.newRequestQueue(this)

        val url = "https://vedicguruji.com/api/experts"
        val jsonObjectRequest =
            object : JsonObjectRequest(Method.GET, url, null, Response.Listener {

                try {

                    val success = it.getInt("status")

                    if (success == 1) {
                        val data = it.getJSONArray("data")
                        val expertJsonObject = data.getJSONObject(position.toInt())

                        expNameDesc.text = expertJsonObject.getString("name")
                        experienceDesc.text = expertJsonObject.getString("experience")
                        statusDesc.text = expertJsonObject.getString("work_status")
                        pricing.text = expertJsonObject.getString("rate_per_min")
                        maindesc.text = expertJsonObject.getString("description")

                        Picasso.get().load(intent.getStringExtra("exp_image")).error(R.drawable.people_icon).into(expImageDesc)


                        val comments : JSONArray = expertJsonObject.getJSONArray("comments")
                        for (i in 0 until comments.length()) {
                            val commentJsonObject = comments.getJSONObject(i)
                            val commentObject = Comment(
                                commentJsonObject.getString("name"),
                                commentJsonObject.getString("rating"),
                                commentJsonObject.getString("comment")
                            )
                            commentInfoList.add(commentObject)



                            recyclerAdapter =
                                CommentRecyclerAdapter(this, commentInfoList)
                            layoutManager = LinearLayoutManager(this)

                            recyclerComment.adapter = recyclerAdapter
                            recyclerComment.layoutManager = layoutManager

                        }

                    } else {
                        Toast.makeText(
                           this@DescriptionActivity,
                            "Some Error Occured!",
                            Toast.LENGTH_LONG
                        ).show()
                    }


                } catch (e: JSONException) {
                    Toast.makeText(
                        this@DescriptionActivity,
                        "Some Unexpected Error Occured!",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
                , Response.ErrorListener {

                        Toast.makeText(
                            this@DescriptionActivity,
                            "Volley Error Occurred",
                            Toast.LENGTH_LONG
                        ).show()


                })            {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String , String>()

                    headers["Content-type"] = ""
                    headers["token"] = ""
                    return headers



                }


            }
        queue.add(jsonObjectRequest)


    }
}
