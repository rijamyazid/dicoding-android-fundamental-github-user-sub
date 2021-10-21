package com.example.githubuser.util

import com.example.githubuser.datasource.remote.response.UserDetailResponse
import com.example.githubuser.util.Helpers.convertToDomain
import com.example.githubuser.view.ui.viewpager2.FollowersFragment
import com.example.githubuser.view.ui.viewpager2.FollowingFragment

object DataConstant {

    val fakeUser1 = UserDetailResponse()
    private val fakeUser2 = UserDetailResponse()
    val listUserRemote = mutableListOf(fakeUser1, fakeUser2)
    val listUserLocal = mutableListOf(fakeUser1.convertToDomain(), fakeUser2.convertToDomain())

    val nullUserData = UserDetailResponse().convertToDomain()

    val detailFragmentPager = mapOf(
        "Followers" to FollowersFragment(),
        "Following" to FollowingFragment()
    )
}