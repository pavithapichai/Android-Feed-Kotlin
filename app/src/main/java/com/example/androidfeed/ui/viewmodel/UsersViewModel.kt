package com.example.androidfeed.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.androidfeed.data.model.User
import com.example.androidfeed.domain.repository.UsersRepository

class UsersViewModel(application: Application): AndroidViewModel(application) {
    private var usersRepository:UsersRepository?=null
    var userData:MutableLiveData<List<User>?>?=null
    init {
        usersRepository = UsersRepository()
        userData = MutableLiveData()
    }

    fun fetchUserByUsername(username:String){
        userData = usersRepository?.getUserByUsername(username)
    }

}