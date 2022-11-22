package com.example.androidfeed.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidfeed.data.model.User
import com.example.androidfeed.domain.repository.UsersRepository

class UsersViewModel(application: Application):AndroidViewModel(application) {
    private var usersRepository:UsersRepository?=null
    var allUserData:MutableLiveData<List<User>?>?=null
    var userData:MutableLiveData<List<User>?>?=null
    init {
        usersRepository = UsersRepository()
        allUserData = MutableLiveData()
        userData = MutableLiveData()
    }

    fun fetchAllUsers(){
        allUserData = usersRepository?.getAllUsers()
    }
    fun fetchUserByUsername(username:String){
        userData = usersRepository?.getUserByUsername(username)
    }

}