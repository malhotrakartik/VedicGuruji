package com.example.vedicguruji.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import com.example.vedicguruji.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class DescriptionActivity : AppCompatActivity() {
    lateinit var toolbar : androidx.appcompat.widget.Toolbar
    lateinit var expImageDesc : ImageView
    lateinit var expNameDesc : TextView
    lateinit var ratingDesc : TextView
    lateinit var statusDesc : TextView
    lateinit var experienceDesc : TextView
    lateinit var pricing : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        toolbar = findViewById(R.id.toolbar)
        expImageDesc = findViewById(R.id.expImageDesc)
        expNameDesc = findViewById(R.id.expNameDesc)
        ratingDesc = findViewById(R.id.ratingDesc)
        statusDesc = findViewById(R.id.statusDesc)
        experienceDesc = findViewById(R.id.experienceDesc)
        pricing = findViewById(R.id.pricing)

        expNameDesc.text = intent.getStringExtra("exp_name")
        experienceDesc.text = intent.getStringExtra("exp_experience")
        statusDesc.text = intent.getStringExtra("exp_status")
        Picasso.get().load(intent.getStringExtra("exp_image")).error(R.drawable.people_icon).into(expImageDesc)








    }
}
