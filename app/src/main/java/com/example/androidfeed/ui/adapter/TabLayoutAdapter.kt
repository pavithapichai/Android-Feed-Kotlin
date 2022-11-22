package com.example.androidfeed.ui.adapter

import android.content.Context
import android.os.Bundle
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidfeed.ui.view.PostFragment
import com.example.androidfeed.ui.view.UserDetailsFragment

class TabLayoutAdapter(fm:FragmentActivity,var tabs:Int):
    FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
       return tabs
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return UserDetailsFragment()
            1-> return PostFragment()
            else  -> {
                return UserDetailsFragment()
            }
        }

    }

}