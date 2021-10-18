package com.example.githubuser.util

import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.remote.response.UserResponse
import com.example.githubuser.util.Helpers.convertToDomain

object FakeData {

    val fakeUser1 = UserResponse(
        56995,
        12,
        "Jake Wharton",
        "Google, Inc.",
        "Pittsburgh, PA, USA",
        "@drawable/user1",
        102,
        "JakeWharton"
    )
    private val fakeUser2 = UserResponse(
        5153,
        2,
        "AMIT SHEKHAR",
        "@MindOrksOpenSource",
        "New Delhi, India",
        "@drawable/user2",
        37,
        "amitshekhariitbhu"
    )
    val listUserRemote = mutableListOf(fakeUser1, fakeUser2)
    val listUserLocal = mutableListOf(fakeUser1.convertToDomain(), fakeUser2.convertToDomain())

    val nullUserData = UserModel(
        name = "null",
        username = "null",
        company = "null",
        avatar = "@drawable/ic_account_circle_24",
        follower = -1,
        following = -1,
        repository = -1,
        location = "null"
    )
}