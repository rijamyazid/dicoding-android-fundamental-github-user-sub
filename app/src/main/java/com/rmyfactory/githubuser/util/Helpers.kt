package com.rmyfactory.githubuser.util

import com.rmyfactory.githubuser.datasource.local.model.UserModel
import com.rmyfactory.githubuser.datasource.remote.response.UserDetailResponse

object Helpers {

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