package com.example.githubuser.datasource.remote

import com.example.githubuser.datasource.remote.response.UserResponse

interface RemoteDataSource {
    fun getAllUsers(): RemoteSealed<List<UserResponse>>
}