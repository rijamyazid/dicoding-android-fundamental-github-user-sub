package com.example.githubuser.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @field:SerializedName("follower") val follower: Int,
    @field:SerializedName("following") val following: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("company") val company: String,
    @field:SerializedName("location") val location: String,
    @field:SerializedName("avatar") val avatar: String,
    @field:SerializedName("repository") val repository: Int,
    @field:SerializedName("username") val username: String
)