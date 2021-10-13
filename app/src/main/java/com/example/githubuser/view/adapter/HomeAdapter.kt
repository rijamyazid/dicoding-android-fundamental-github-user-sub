package com.example.githubuser.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.databinding.ItemHolderUserBinding
import com.example.githubuser.datasource.remote.response.UserResponse

abstract class HomeAdapter : RecyclerView.Adapter<HomeAdapter.UserViewHolder>() {

    private val itemList = ArrayList<UserResponse>()

    fun setItems(itemList: ArrayList<UserResponse>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    abstract fun onBindData(viewHolder: UserViewHolder, data: UserResponse)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemHolderUserBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        this.onBindData(holder, itemList[position])
    }

    class UserViewHolder(val binding: ItemHolderUserBinding) : RecyclerView.ViewHolder(binding.root)

}