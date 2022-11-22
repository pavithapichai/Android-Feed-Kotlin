package com.example.androidfeed.data.remote.api

import com.example.androidfeed.data.model.Post
import com.example.androidfeed.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("users")
    fun fetchAllUsers(): Call<List<User>>
    @GET("users")
    fun fetchUser(@Query("username") username: String): Call<List<User>>
    @GET("posts")
    fun fetchAllPostsById(@Query("userId") userId: Int): Call<List<Post>>

    @GET("posts")
    fun fetchAllPosts(): Call<List<Post>>
}