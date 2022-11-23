package com.example.androidfeed.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.androidfeed.R
import com.example.androidfeed.databinding.FragmentPostBinding
import com.example.androidfeed.databinding.FramentUserDetailsBinding
import com.example.androidfeed.ui.viewmodel.UserDetailsViewModel


class UserDetailsFragment():Fragment() {
    private lateinit var binding: FramentUserDetailsBinding
    private val vm: UserDetailsViewModel  by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FramentUserDetailsBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = vm.getData()
        vm.user?.observe(viewLifecycleOwner, Observer {
            binding.userName.text = it.name
            binding.txtEmail.text = it.email
            binding.txtPhone.text = it.phone
        })

    }

}