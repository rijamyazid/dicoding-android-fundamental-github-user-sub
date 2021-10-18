package com.example.githubuser.util

import com.example.githubuser.R
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.remote.response.UserDetailResponse

object Helpers {

    const val CODE_EMPTY = "EMPTY"

    private fun imgPair(): Map<String, Int> = mapOf(
        "@drawable/user1" to R.drawable.user1,
        "@drawable/user2" to R.drawable.user2,
        "@drawable/user3" to R.drawable.user3,
        "@drawable/user4" to R.drawable.user4,
        "@drawable/user5" to R.drawable.user5,
        "@drawable/user6" to R.drawable.user6,
        "@drawable/user7" to R.drawable.user7,
        "@drawable/user8" to R.drawable.user8,
        "@drawable/user9" to R.drawable.user9,
        "@drawable/user10" to R.drawable.user10,
        "@drawable/ic_account_circle_24" to R.drawable.ic_account_circle_24
    )

    fun List<UserDetailResponse>.convertToDomain() = this.map { it.convertToDomain() }

    fun UserDetailResponse.convertToDomain() = UserModel(
        avatar = this.avatarUrl ?: "null",
        name = this.name ?: "null",
        username = this.login ?: "null",
        location = this.location ?: "null",
        company = this.company ?: "null",
        repository = this.publicRepos ?: -1,
        follower = this.followers ?: -1,
        following = this.following ?: -1
    )

    fun UserModel.validateNull(): Boolean {
        return when {
            avatar == "null" -> true
            name == "null" -> true
            location == "null" -> true
            company == "null" -> true
            else -> false
        }
    }
}