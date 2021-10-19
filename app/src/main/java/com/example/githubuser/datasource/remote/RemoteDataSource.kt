package com.example.githubuser.datasource.remote

import com.example.githubuser.datasource.local.model.UserModel

interface RemoteDataSource {
    suspend fun getUsers(): RemoteSealed<List<UserModel>>
    suspend fun getUsersByQuery(query: String): RemoteSealed<List<UserModel>>
}