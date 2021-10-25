package com.example.githubuser.datasource.local

import androidx.lifecycle.LiveData
import com.example.githubuser.datasource.local.model.UserModel

interface LocalDataSource {

    fun readUsers(): LiveData<List<UserModel>>
    fun readUser(username: String): LiveData<UserModel>
    suspend fun updateUser(user: UserModel)
    suspend fun insertUser(user: UserModel)
    suspend fun insertUsers(users: List<UserModel>)
}