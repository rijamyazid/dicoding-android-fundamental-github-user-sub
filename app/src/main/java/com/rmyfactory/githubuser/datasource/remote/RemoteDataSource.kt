package com.rmyfactory.githubuser.datasource.remote

import androidx.lifecycle.LiveData
import com.rmyfactory.githubuser.datasource.local.LocalSealed
import com.rmyfactory.githubuser.datasource.local.model.UserModel

interface RemoteDataSource {
    fun getUsers(): LiveData<LocalSealed<List<UserModel>>>
    fun getUsersByQuery(query: String): LiveData<LocalSealed<List<UserModel>>>
    fun getFollowers(username: String): LiveData<LocalSealed<List<UserModel>>>
    fun getFollowing(username: String): LiveData<LocalSealed<List<UserModel>>>
}