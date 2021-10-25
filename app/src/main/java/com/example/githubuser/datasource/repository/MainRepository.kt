package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel

interface MainRepository {

    fun getUsers(): LiveData<LocalSealed<List<UserModel>>>
    fun getUser(username: String): LiveData<UserModel>
    fun getUsersByQuery(query: String?): LiveData<LocalSealed<List<UserModel>>>
    fun getFavoriteUsers(): LiveData<List<UserModel>>
    fun getFollowers(username: String): LiveData<LocalSealed<List<UserModel>>>
    fun getFollowing(username: String): LiveData<LocalSealed<List<UserModel>>>
    suspend fun updateUser(user: UserModel)
    suspend fun insertUser(user: UserModel)
}