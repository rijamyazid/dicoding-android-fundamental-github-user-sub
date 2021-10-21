package com.example.githubuser.view.ui.viewpager2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var username: String = ""

    abstract fun fragmentList(): List<Fragment>

    fun setArguments(arguments: String = "") {
        this.username = arguments
    }

    override fun getItemCount() = fragmentList().size

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