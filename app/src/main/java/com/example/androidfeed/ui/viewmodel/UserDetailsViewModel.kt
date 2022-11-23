package com.example.androidfeed.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.androidfeed.data.model.Post
import com.example.androidfeed.data.model.User
import com.example.androidfeed.domain.repository.PostRepository
import com.example.androidfeed.domain.repository.UsersRepository

class UserDetailsViewModel(application:Application):AndroidViewModel(application) {
    private var postRepository: PostRepository?=null
    var allUserData: MutableLiveData<List<Post>?>?=null
    var postData: MutableLiveData<List<Post>?>?=null
    private var userId =0
    var user:MutableLiveData<User>? =null
    init {
        postRepository = PostRepository()
        allUserData = MutableLiveData()
        postData = MutableLiveData()
        user = MutableLiveData()
    }

    fun fetchAllPostsById(){
        postData = postRepository?.getpostsByUserId(this.userId)
    }

    fun setData( user:User){
        this.userId = user.id
        this.user?.value = user
    }

    fun getData() : User?{
        return this.user?.value
    }
}