package com.example.githubuser.util

import com.example.githubuser.datasource.remote.response.UserDetailResponse
import com.example.githubuser.util.Helpers.convertToDomain

object FakeData {

    val fakeUser1 = UserDetailResponse()
    private val fakeUser2 = UserDetailResponse()
    val listUserRemote = mutableListOf(fakeUser1, fakeUser2)
    val listUserLocal = mutableListOf(fakeUser1.convertToDomain(), fakeUser2.convertToDomain())

    val nullUserData = UserDetailResponse().convertToDomain()
}