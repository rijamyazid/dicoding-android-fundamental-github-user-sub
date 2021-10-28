package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.githubuser.datasource.local.LocalDataSource
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.remote.RemoteDataSource
import com.example.githubuser.datasource.remote.RemoteSealed

class MainRepositoryImplMirror(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MainRepository {

    override fun getUsers(): LiveData<LocalSealed<List<UserModel>>> {

        return object : NetworkBoundResource<List<UserModel>, List<UserModel>>() {
            override fun fetchFromLocal(): LiveData<List<UserModel>> {
                return localDataSource.readUsers()
            }

            override fun shouldFetch(localData: List<UserModel>?): Boolean {
                return localData == null || localData.isEmpty() || localData.size < 10
            }

            override fun fetchFromNetwork(): LiveData<LocalSealed<List<UserModel>>> {
                return remoteDataSource.getUsers()
            }

            override suspend fun saveToLocal(remoteData: List<UserModel>) {
                localDataSource.insertUsers(remoteData)
            }
        }.asLiveData()

    }

    override fun getUser(username: String): LiveData<UserModel> {
        return localDataSource.readUser(username)
    }

    override fun getUsersByQuery(query: String?): LiveData<LocalSealed<List<UserModel>>> {
        return if (query.isNullOrEmpty()) getUsers()
        else remoteDataSource.getUsersByQuery(query)
    }

    override fun getFavoriteUsers(): LiveData<List<UserModel>> {
        return localDataSource.readFavoriteUsers()
    }

    override fun getFollowers(username: String): LiveData<LocalSealed<List<UserModel>>> {
        return remoteDataSource.getFollowers(username)
    }

    override fun getFollowing(username: String): LiveData<LocalSealed<List<UserModel>>> {
        return remoteDataSource.getFollowing(username)
    }

    override suspend fun updateUser(user: UserModel) {
        localDataSource.updateUser(user)
    }

    override suspend fun insertUser(user: UserModel) {
        localDataSource.insertUser(user)
    }

}