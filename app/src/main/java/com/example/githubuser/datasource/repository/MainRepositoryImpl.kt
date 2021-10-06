package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.githubuser.datasource.remote.RemoteDataSource
import com.example.githubuser.datasource.remote.RemoteSealed
import com.example.githubuser.datasource.remote.response.UserResponse
import javax.inject.Inject

class MainRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource) : MainRepository {

    override fun getAllUsers(): LiveData<RemoteSealed<List<UserResponse>>> = liveData {
        when (val response = remoteDataSource.getAllUsers()) {
            is RemoteSealed.Value -> {
                emit(RemoteSealed.Value(response.data))
            }
            is RemoteSealed.Error -> {
                emit(RemoteSealed.Error(response.message))
            }
        }
    }

}