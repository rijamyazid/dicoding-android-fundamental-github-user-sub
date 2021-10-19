package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.remote.RemoteDataSource
import com.example.githubuser.datasource.remote.RemoteSealed
import javax.inject.Inject

class MainRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource) : MainRepository {

    override fun getUsers(): LiveData<LocalSealed<List<UserModel>>> = liveData {
        emit(LocalSealed.Loading(true))
        when (val response = remoteDataSource.getUsers()) {
            is RemoteSealed.Value -> {
                emit(LocalSealed.Value(response.data))
            }
            is RemoteSealed.Error -> {
                emit(LocalSealed.Error(response.message))
            }
        }
    }

    override fun getUsersByQuery(query: String?): LiveData<LocalSealed<List<UserModel>>> =
        liveData {
            emit(LocalSealed.Loading(true))
            when (val response = remoteDataSource.getUsersByQuery(query)) {
                is RemoteSealed.Value -> {
                    emit(LocalSealed.Value(response.data))
                }
                is RemoteSealed.Error -> {
                    emit(LocalSealed.Error(response.message))
                }
            }
        }

}