package com.example.androidfeed.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfeed.R
import com.example.androidfeed.databinding.FragmentPostBinding
import com.example.androidfeed.ui.adapter.PostAdapter
import com.example.androidfeed.ui.viewmodel.UserDetailsViewModel
import com.example.androidfeed.ui.viewmodel.UsersViewModel

class PostFragment():Fragment() {
    private lateinit var postAdapter: PostAdapter
    private lateinit var binding: FragmentPostBinding
    private val vm: UserDetailsViewModel  by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // vm = ViewModelProvider(this)[UserDetailsViewModel::class.java]
        vm.fetchAllPostsById()
        vm.postData?.observe(viewLifecycleOwner, Observer {

            if (it != null && it.isEmpty()) //showToast("Please enter valid user name")
            else {

                binding.listPost.setHasFixedSize(true)
                binding.listPost.layoutManager=LinearLayoutManager(requireContext())

                postAdapter = PostAdapter(it)
                binding.listPost.adapter=postAdapter
            }

        })
    }
}