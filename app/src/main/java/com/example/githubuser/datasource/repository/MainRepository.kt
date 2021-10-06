package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import com.example.githubuser.datasource.remote.RemoteSealed
import com.example.githubuser.datasource.remote.response.UserResponse

interface MainRepository {

    fun getAllUsers(): LiveData<RemoteSealed<List<UserResponse>>>

}