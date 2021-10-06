package com.example.githubuser.datasource.remote

import android.content.Context
import com.example.githubuser.datasource.remote.response.MainResponse
import com.example.githubuser.datasource.remote.response.UserResponse
import com.google.gson.Gson
import javax.inject.Inject

class RemoteDataSourceImpl
@Inject constructor(private val context: Context, private val gson: Gson) : RemoteDataSource {

    override fun getAllUsers(): RemoteSealed<List<UserResponse>> {
        val getUsers = gson.fromJson(
            context.applicationContext.assets.open("githubuser.json").bufferedReader(),
            MainResponse::class.java
        ).users
        return RemoteSealed.Value(getUsers)
    }

}