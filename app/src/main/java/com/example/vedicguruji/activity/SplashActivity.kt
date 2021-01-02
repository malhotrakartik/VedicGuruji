package com.example.vedicguruji.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vedicguruji.R
import java.lang.Exception

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val background = object : Thread(){
            override fun run() {
                try {
                    Thread.sleep(1000)
                    val intent = Intent(this@SplashActivity , MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }catch (e : Exception){
                    e.printStackTrace()

                }
            }
        }
        background.start()



    }
}