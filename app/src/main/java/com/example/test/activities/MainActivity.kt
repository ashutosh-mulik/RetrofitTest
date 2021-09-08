package com.example.test.activities

import Post
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.test.R
import com.example.test.services.PostInterface
import com.example.test.services.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    //private lateinit var txtData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.main_list_view)
        //txtData = findViewById(R.id.main_txt_data)

        PostService.getInstance.getPosts().enqueue(object : Callback<List<Post>>{

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val list = response.body()!!
                val listOfTitles = mutableListOf<String>()

                list.forEach {
                    listOfTitles.add(it.title)
                    Log.d("Data", "onResponse: ${it.title}")
                }

                //txtData.text = listOfTitles.toString()
                val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, listOfTitles)
                listView.adapter = adapter

            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

        })

    }
}