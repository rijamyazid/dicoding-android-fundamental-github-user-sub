package com.example.githubuser.util

import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.remote.response.UserDetailResponse
import com.example.githubuser.util.Helpers.convertToDomain

object DataConstant {

    val fakeUser1 = UserModel(
        name = "Tom Preston-Werner",
        username = "mojombo",
        location = "San Francisco",
        company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
        repository = 63,
        follower = 22717,
        following = 11,
        avatar = "https://avatars.githubusercontent.com/u/1?v=4"
    )
    private val fakeUser2 = UserModel(
        name = "PJ Hyett",
        username = "pjhyett",
        location = "San Francisco",
        company = "GitHub, Inc.",
        repository = 8,
        follower = 8220,
        following = 30,
        avatar = "https://avatars.githubusercontent.com/u/3?v=4"
    )

    val fakeUserByQueryandUsername = mapOf(
        "Query" to listOf(fakeUser1),
        "Username1" to listOf(fakeUser2),
        "Username2" to listOf(fakeUser1)
    )

    val listUsers = mutableListOf(fakeUser1, fakeUser2)

    val nullDataUser = UserDetailResponse().convertToDomain()

    val detailTabNames = listOf("Followers", "Following")
}