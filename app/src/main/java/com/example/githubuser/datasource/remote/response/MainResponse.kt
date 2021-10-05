package com.example.githubuser.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @field:SerializedName("users") val users: List<UsersResponse>
)