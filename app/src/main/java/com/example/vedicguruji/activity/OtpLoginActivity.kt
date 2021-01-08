package com.example.vedicguruji.activity

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
import com.android.volley.toolbox.StringRequest
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
            jsonParams.put("action","send_otp")
            jsonParams.put("mobile_number",etEnterNumber.text)


            val stringRequest : StringRequest =object :
                 StringRequest(Request.Method.POST, url, Response.Listener {

                    try{
                        val success = it[0].toInt()
                        if(success == 123){
                            Toast.makeText(this@OtpLoginActivity,"Otp Sent",Toast.LENGTH_LONG).show()
                            rlSendOtp.visibility = View.GONE;
                            rlVerifyOtp.visibility = View.VISIBLE;
                            stepView.go(1,false);


                        }else{

                            Toast.makeText(
                                this@OtpLoginActivity,
                                "some error occurred $success",
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

                        headers["Content-type"] = "application/x-www-form-urlencoded"
                        headers["token"] = ""
                        return headers



                    }
                override fun getBodyContentType(): String? {
                    return "application/x-www-form-urlencoded; charset=UTF-8"
                }

                    override fun getParams(): MutableMap<String, String> {

                        val params = HashMap<String , String>()

                        params["action"] = "send_otp"
                        params["mobile_number"] = etEnterNumber.text.toString()
                        return params


                    }
                }
            queue.add(stringRequest)



        }

        btnVerifyOtp.setOnClickListener {
            val queue = Volley.newRequestQueue(this@OtpLoginActivity)
            val url = "https://vedicguruji.com/api/customer_login_verification"

            val jsonParams = JSONObject()
            jsonParams.put("mobile_number",etEnterNumberAgain.text)
            jsonParams.put("action","verify_otp")
            jsonParams.put("otp_value",etEnterOtp.text)

            val stringRequest : StringRequest =
                object : StringRequest(Request.Method.POST, url, Response.Listener {

                    try{
                        val success = it[0].toInt()
                        if(success == 123){

                            rlSendOtp.visibility = View.GONE;
                            rlVerifyOtp.visibility = View.GONE;
                            stepView.go(2,false);
                            Toast.makeText(this@OtpLoginActivity,"number verified",Toast.LENGTH_LONG).show()



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

                    override fun getBodyContentType(): String? {
                        return "application/x-www-form-urlencoded; charset=UTF-8"
                    }

                    override fun getParams(): MutableMap<String, String> {

                        val params = HashMap<String , String>()

                        params["action"] = "verify_otp"
                        params["mobile_number"] = etEnterNumberAgain.text.toString()
                        params["otp_value"] = etEnterOtp.text.toString()
                        return params


                    }
                }
            queue.add(stringRequest)

        }









    }
}


