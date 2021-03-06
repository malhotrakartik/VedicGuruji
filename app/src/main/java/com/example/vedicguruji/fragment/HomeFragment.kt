package com.example.vedicguruji.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.vedicguruji.R
import com.example.vedicguruji.adapter.CommentRecyclerAdapter
import com.example.vedicguruji.adapter.HomeRecyclerAdapter
import com.example.vedicguruji.adapter.LiveRecyclerAdapter

import com.example.vedicguruji.model.Expert
import com.example.vedicguruji.model.SlideItem
import com.example.vedicguruji.utils.ConnectionManager
import org.json.JSONException

class HomeFragment : Fragment() {
    lateinit var recyclerHome: RecyclerView
    lateinit var recyclerLive : RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var liveLayoutManager: LinearLayoutManager

    lateinit var recyclerAdapter: HomeRecyclerAdapter
    lateinit var liveRecyclerAdapter: LiveRecyclerAdapter


    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    val expertInfoList = arrayListOf<Expert>()
    val liveInfoList = arrayListOf<SlideItem>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerHome = view.findViewById(R.id.recyclerHome)
        recyclerLive = view.findViewById(R.id.recyclerLive)

        layoutManager = LinearLayoutManager(activity)
        liveLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        for (j in 0 until 5){

            val liveObject = SlideItem(
                "hello",
                "https://www.imagesource.com/wp-content/uploads/2019/06/Rio.jpg"
            )
            liveInfoList.add(liveObject)

            liveRecyclerAdapter = LiveRecyclerAdapter(activity as Context,liveInfoList)
            recyclerLive.adapter = liveRecyclerAdapter
            recyclerLive.layoutManager = liveLayoutManager

        }







        progressBar = view.findViewById(R.id.progressBar)
        progressLayout = view.findViewById(R.id.progressLayout)


        progressLayout.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(activity as Context)

        val url = "https://vedicguruji.com/api/experts"
        if (ConnectionManager().checkConnectivity(activity as Context)){
        val jsonObjectRequest =
            object : JsonObjectRequest(Method.GET, url, null, Response.Listener {

                try {
                    progressLayout.visibility = View.GONE
                    val success = it.getInt("status")

                    if (success == 1) {
                        val data = it.getJSONArray("data")
                        for (i in 0 until data.length()) {
                            val expertJsonObject = data.getJSONObject(i)
                            val expertObject = Expert(
                                expertJsonObject.getString("expert_image"),
                                expertJsonObject.getString("name"),
                                expertJsonObject.getString("work_status"),
                                expertJsonObject.getString("rate_per_min"),
                                expertJsonObject.getString("experience"),
                                expertJsonObject.getString("description")
//





                            )
                            expertInfoList.add(expertObject)

                            recyclerAdapter =
                                HomeRecyclerAdapter(activity as Context, expertInfoList)
                            layoutManager = GridLayoutManager(activity as Context , 2)

                            recyclerHome.adapter = recyclerAdapter
                            recyclerHome.layoutManager = layoutManager

                        }

                    } else {
                        Toast.makeText(
                            activity as Context,
                            "Some Error Occured!",
                            Toast.LENGTH_LONG
                        ).show()
                    }


                } catch (e: JSONException) {
                    Toast.makeText(
                        activity as Context,
                        "Some Unexpected Error Occured!",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
                , Response.ErrorListener {
                    if (activity != null) {
                        Toast.makeText(
                            activity as Context,
                            "Volley Error Occurred",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })            {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String , String>()

                    headers["Content-type"] = ""
                    headers["token"] = ""
                    return headers



                }
            }
            queue.add(jsonObjectRequest)


    }else{
            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("success")
            dialog.setMessage("Internet NOT Connection Found")
            dialog.setPositiveButton("Open Settings"){
                    text,listener ->

                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                activity?.finish()



            }
            dialog.setNegativeButton("Cancel"){
                    text,listener ->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()

        }
            return view




    }
}