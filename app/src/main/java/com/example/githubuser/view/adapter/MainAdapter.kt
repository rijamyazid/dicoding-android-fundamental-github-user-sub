package com.example.githubuser.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.databinding.ItemHolderUserBinding

abstract class MainAdapter : RecyclerView.Adapter<MainAdapter.UserViewHolder>() {

    private val itemList = ArrayList<Any>()

    fun setItems(itemList: ArrayList<Any>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    abstract fun onBindData(position: Int, viewHolder: UserViewHolder, data: Any)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemHolderUserBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        this.onBindData(position, holder, itemList[position])
    }

    class UserViewHolder(val binding: ItemHolderUserBinding) : RecyclerView.ViewHolder(binding.root)

}