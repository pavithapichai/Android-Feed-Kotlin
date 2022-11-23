package com.example.androidfeed.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.androidfeed.data.model.Post
import com.example.androidfeed.data.model.User
import com.example.androidfeed.data.remote.api.ApiClient
import com.example.androidfeed.data.remote.api.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository {
    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApi()
    }

    fun getpostsByUserId(userId:Int): MutableLiveData<List<Post>?> {
        val data = MutableLiveData<List<Post>?>()
        apiInterface?.fetchAllPostsById(userId)?.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val res = response.body()
                if (response.code() == 200 && res != null)
                    data.value = res
                else
                    data.value = null
            }
        })
        return data
    }
}