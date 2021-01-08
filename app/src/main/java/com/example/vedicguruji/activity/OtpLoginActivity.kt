package com.example.vedicguruji.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.vedicguruji.R
import com.shuhart.stepview.StepView
import org.json.JSONException
import org.json.JSONObject

class OtpLoginActivity : AppCompatActivity() {
    lateinit var stepView : StepView
    lateinit var rlSendOtp : RelativeLayout
    lateinit var rlVerifyOtp : RelativeLayout
    lateinit var btnSendOtp : Button
    lateinit var btnVerifyOtp : Button
    lateinit var etEnterNumber : EditText
    lateinit var etEnterNumberAgain : EditText
    lateinit var etEnterOtp : EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_login)

        stepView = findViewById(R.id.step_view)
        rlSendOtp = findViewById(R.id.rlSendOtp)
        rlVerifyOtp = findViewById(R.id.rlVerifyOtp)
        btnSendOtp = findViewById(R.id.btnSendOtp)
        btnVerifyOtp = findViewById(R.id.btnVerifyOtp)
        etEnterNumber = findViewById(R.id.etEnterNumber)
        etEnterNumberAgain = findViewById(R.id.etEnterNumberAgain)
        etEnterOtp = findViewById(R.id.etEnterOtp)



//            normalLayout.setVisibility(View.GONE);
        stepView.visibility = View.VISIBLE
        stepView.setStepsNumber(2)
        stepView.go(0, false)

        btnSendOtp.setOnClickListener {
            val queue = Volley.newRequestQueue(this@OtpLoginActivity)
            val url = "https://vedicguruji.com/api/customer_login_verification"

            val jsonParams = JSONObject()
            jsonParams.put("mobile_number",etEnterNumber.text)

            val jsonRequest =
                object : JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener {

                    try{
                        val success = it.getInt("status")
                        if(success == 1){
                            Toast.makeText(this@OtpLoginActivity,"Otp Sent",Toast.LENGTH_LONG).show()
                            rlSendOtp.visibility = View.GONE;
                            rlVerifyOtp.visibility = View.VISIBLE;
                            stepView.go(1,false);


                        }else{
                            Toast.makeText(
                                this@OtpLoginActivity,
                                "Some Error Occured!,Try Again",
                                Toast.LENGTH_LONG
                            ).show()
                        }


                    }catch (e: JSONException) {
                        Toast.makeText(
                            this@OtpLoginActivity,
                            "Some Unexpected Error Occured!",
                            Toast.LENGTH_LONG
                        ).show()
                    }



                }, Response.ErrorListener {

                        Toast.makeText(
                            this@OtpLoginActivity,
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
            queue.add(jsonRequest)



        }

        btnVerifyOtp.setOnClickListener {
            val queue = Volley.newRequestQueue(this@OtpLoginActivity)
            val url = "https://vedicguruji.com/api/customer_login_verification"

            val jsonParams = JSONObject()
            jsonParams.put("mobile_number",etEnterNumberAgain.text)

            val jsonRequest =
                object : JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener {

                    try{
                        val success = it.getInt("status")
                        if(success == 1){
                            Toast.makeText(this@OtpLoginActivity,"Otp Sent",Toast.LENGTH_LONG).show()
                            rlSendOtp.visibility = View.GONE;
                            rlVerifyOtp.visibility = View.VISIBLE;
                            stepView.go(1,false);


                        }else{
                            Toast.makeText(
                                this@OtpLoginActivity,
                                "Some Error Occured!,Try Again",
                                Toast.LENGTH_LONG
                            ).show()
                        }


                    }catch (e: JSONException) {
                        Toast.makeText(
                            this@OtpLoginActivity,
                            "Some Unexpected Error Occured!",
                            Toast.LENGTH_LONG
                        ).show()
                    }



                }, Response.ErrorListener {

                    Toast.makeText(
                        this@OtpLoginActivity,
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
            queue.add(jsonRequest)

        }









    }
}


