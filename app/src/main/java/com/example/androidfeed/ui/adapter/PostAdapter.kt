package com.example.androidfeed.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfeed.data.model.Post
import com.example.androidfeed.databinding.FragmentPostBinding
import com.example.androidfeed.databinding.ItemPostBinding


class PostAdapter(var listPost: List<Post>?) : RecyclerView.Adapter<MyViewHolder>() {
    private lateinit var itemPostBinding: ItemPostBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        itemPostBinding = ItemPostBinding.inflate(layoutInflater, parent, false)
        val view = itemPostBinding
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.txtBody.text = listPost?.get(position)?.body
        holder.view.txtTitle.text = listPost?.get(position)?.title
    }

    override fun getItemCount(): Int {
        return listPost?.size ?: 0
    }
}


class MyViewHolder(val view: ItemPostBinding) : RecyclerView.ViewHolder(view.root) {

}