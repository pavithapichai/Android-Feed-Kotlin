package com.example.androidfeed.ui.view

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
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

import com.example.androidfeed.databinding.ActivityUserBinding
import com.example.androidfeed.databinding.ActivityUserDetailsBinding
import com.example.androidfeed.ui.viewmodel.UsersViewModel
import com.example.androidfeed.util.Constants
import com.example.androidfeed.util.NetworkListner
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class UserActivity : AppCompatActivity() ,NetworkListner.ConnectivityReceiverListener{
    private lateinit var vm: UsersViewModel
    private var snackBar: Snackbar? = null
    lateinit var userDetailsBinding: ActivityUserBinding
    lateinit var networkReceiver:NetworkListner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         userDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_user)
        networkReceiver = NetworkListner()
        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        vm = ViewModelProvider(this)[UsersViewModel::class.java]
        userDetailsBinding.nextbutton.setOnClickListener {


            userDetailsBinding.pbLoading.visibility = ProgressBar.VISIBLE
            var name = userDetailsBinding.editTextUserName.text.toString()
            if (!isNameValid(name)) {
                showDialog(getString(R.string.alert_1))
                userDetailsBinding.pbLoading.visibility = ProgressBar.INVISIBLE
            } else {
                vm.fetchUserByUsername(name)
                vm.userData?.observe(this, Observer {
                    userDetailsBinding.pbLoading.visibility = ProgressBar.INVISIBLE
                    if (it.isNullOrEmpty()) showDialog(getString(R.string.alert_2))
                    else {

                        if(it.get(0) !=null){
                            val intent = Intent(UserActivity(), UserDetailsActivity::class.java).apply {
                                putExtra(Constants.USER_KEY, it.get(0))
                            }
                            // start your next activity
                            startActivity(intent)
                        }

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

    override fun onStart() {
        super.onStart()
        registerReceiver(networkReceiver,IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    private fun showDialog(title: String) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage(title)
        builder.show()

    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }
    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            snackBar = Snackbar.make(userDetailsBinding.root, "You are offline", Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.show()
            userDetailsBinding.nextbutton.isEnabled = false
            userDetailsBinding.nextbutton.alpha =0.5f
        } else {
            snackBar?.dismiss()
            userDetailsBinding.nextbutton.isEnabled = true
            userDetailsBinding.nextbutton.alpha = 1f
        }
    }

    override fun onResume() {
        super.onResume()
        NetworkListner.connectivityReceiverListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(networkReceiver)
    }
    override fun onPause() {
        super.onPause()
        this.unregisterReceiver(networkReceiver)
    }

}