package com.example.androidfeed.ui.view

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidfeed.R
import com.example.androidfeed.data.model.User
import com.example.androidfeed.databinding.ActivityUserDetailsBinding
import com.example.androidfeed.ui.adapter.TabLayoutAdapter
import com.example.androidfeed.ui.viewmodel.UserDetailsViewModel
import com.example.androidfeed.util.Constants
import com.google.android.material.tabs.TabLayout

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var vm: UserDetailsViewModel
    private val fragmentViewModel:UserDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDetailsBinding: ActivityUserDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        var user:User = intent.extras?.get(Constants.USER_KEY) as User

        setSupportActionBar(userDetailsBinding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        userDetailsBinding.toolbar.setNavigationOnClickListener( {
          finish()
        });
        fragmentViewModel.setData(user)

        userDetailsBinding.tabLayout.addTab(
            userDetailsBinding.tabLayout.newTab().setText(getString(R.string.tab_1))
        )
        userDetailsBinding.tabLayout.addTab(
            userDetailsBinding.tabLayout.newTab().setText(getString(R.string.tab_2))
        )
        val adapter = TabLayoutAdapter(
            this,
            userDetailsBinding.tabLayout.tabCount
        )
        userDetailsBinding.viewPager.adapter = adapter

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