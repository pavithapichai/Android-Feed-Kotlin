package com.example.androidfeed.domain.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidfeed.data.model.User
import com.example.androidfeed.data.remote.api.ApiClient
import com.example.androidfeed.data.remote.api.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersRepository {

    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun getAllUsers(): MutableLiveData<List<User>?> {
        val data = MutableLiveData<List<User>?>()
        apiInterface?.fetchAllUsers()?.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val res = response.body()
                if (response.code() == 200 && res != null)
                    data.value = res
                else
                    data.value = null
            }
        })
        return data
    }

    fun getUserByUsername(userName: String): MutableLiveData<List<User>?> {
        val data = MutableLiveData<List<User>?>()
        apiInterface?.fetchUser(userName)?.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
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
