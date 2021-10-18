package com.example.githubuser.datasource.remote

import com.example.githubuser.datasource.local.model.UserModel

interface RemoteDataSource {
    suspend fun getAllUsers(): RemoteSealed<List<UserModel>>
}