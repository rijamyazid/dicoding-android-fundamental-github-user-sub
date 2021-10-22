package com.example.githubuser.view.ui.viewpager2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.util.DataConstant.detailTabNames

class DetailPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private lateinit var username: String

    fun setArguments(arguments: String = "") {
        this.username = arguments
    }

    override fun getItemCount() = detailTabNames.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FollowersFragment.newInstance(username)
            }
            1 -> {
                FollowingFragment.newInstance(username)
            }
            else -> Fragment()
        }
    }
}