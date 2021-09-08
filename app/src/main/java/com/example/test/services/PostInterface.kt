package com.example.test.services

import Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface PostInterface {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

}

object PostService {

    val getInstance: PostInterface

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        getInstance = retrofit.create(PostInterface::class.java)
    }
}