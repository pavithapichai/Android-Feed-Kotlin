package com.example.androidfeed.ui.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.androidfeed.R
import com.example.androidfeed.data.model.User
import com.example.androidfeed.databinding.ActivityUserDetailsBinding
import com.example.androidfeed.ui.adapter.TabLayoutAdapter
import com.example.androidfeed.ui.viewmodel.UserDetailsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var vm: UserDetailsViewModel
    private val fragmentViewModel:UserDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDetailsBinding: ActivityUserDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        var user:User = intent.extras?.get("user") as User
//        vm = ViewModelProvider(this)[UserDetailsViewModel::class.java]
//        vm.setData(user)
        setSupportActionBar(userDetailsBinding.toolbar)
        supportActionBar?.apply {
            title = "Android Feed"

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        userDetailsBinding.toolbar.setNavigationOnClickListener( {
          finish()
        });
        fragmentViewModel.setData(user)
        val adapter = TabLayoutAdapter(
            this,
            userDetailsBinding.tabLayout.tabCount
        )
        userDetailsBinding.viewPager.adapter = adapter
        userDetailsBinding.tabLayout.addTab(
            userDetailsBinding.tabLayout.newTab().setText("User Details")
        )
        userDetailsBinding.tabLayout.addTab(
            userDetailsBinding.tabLayout.newTab().setText("User Post ")
        )


        userDetailsBinding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                userDetailsBinding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}