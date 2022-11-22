package com.example.androidfeed.ui.view

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidfeed.R
import com.example.androidfeed.data.model.Address
import com.example.androidfeed.data.model.Company
import com.example.androidfeed.data.model.Geo
import com.example.androidfeed.data.model.User
import com.example.androidfeed.databinding.ActivityUserBinding
import com.example.androidfeed.databinding.ActivityUserDetailsBinding
import com.example.androidfeed.ui.viewmodel.UsersViewModel

class UserActivity : AppCompatActivity() {
    private lateinit var vm: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDetailsBinding: ActivityUserBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_user)
        vm = ViewModelProvider(this)[UsersViewModel::class.java]
        vm.fetchAllUsers()
        userDetailsBinding.nextbutton.setOnClickListener {
            userDetailsBinding.pbLoading.visibility = ProgressBar.VISIBLE
            var name = userDetailsBinding.editTextUserName.text.toString()
            if (!isNameValid(name)) {
                showDialog("Please enter username")
                userDetailsBinding.pbLoading.visibility = ProgressBar.INVISIBLE
            } else {
                vm.fetchUserByUsername(name)
                vm.userData?.observe(this, Observer {
                    userDetailsBinding.pbLoading.visibility = ProgressBar.INVISIBLE
                    if (it != null && it.isEmpty()) showDialog("Please enter valid user name")
                    else {
                        it?.get(0);
                        val intent = Intent(this, UserDetailsActivity::class.java).apply {

                            putExtra("user",it?.get(0))
                        }

                        // start your next activity
                        startActivity(intent)
                    }

                })
            }
        }
        vm.userData?.observe(this, Observer {
            Log.e("TAG", it.toString())
        })
    }

    private fun isNameValid(name: String?): Boolean {
        if (name.equals(""))
            return false
        return true;
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun showDialog(title: String) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage(title)
        builder.setPositiveButton(R.string.ok) { dialog, which ->

        }

        builder.show()

    }


}