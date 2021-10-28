package com.example.githubuser.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.databinding.ItemHolderUserBinding
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.view.ui.SearchFragmentDirections

class HomeAdapter(private val view: View,
                  private val onClickListener: (UserModel) -> Unit = {})
    : RecyclerView.Adapter<HomeAdapter.UserViewHolder>() {

    private val itemList = ArrayList<UserModel>()

    fun setItems(itemList: ArrayList<UserModel>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemHolderUserBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBindData(view, itemList[position], onClickListener)
    }

    class UserViewHolder(private val binding: ItemHolderUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBindData(view: View, data: UserModel, onClickListener: (UserModel) -> Unit) {
            with(binding) {
                tvNameItem.text = data.name
                tvCompanyItem.text = data.company
                tvLocationItem.text = data.location
                Glide.with(view.context)
                    .load(data.avatar)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .circleCrop()
                    .into(binding.imgUserItem)

                root.setOnClickListener {
                    onClickListener(data)
                }
            }
        }

    }

}