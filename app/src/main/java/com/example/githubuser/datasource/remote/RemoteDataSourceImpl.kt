package com.example.githubuser.datasource.remote

import android.util.Log
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.remote.api.ApiConfig
import com.example.githubuser.util.Helpers.CODE_EMPTY
import com.example.githubuser.util.Helpers.convertToDomain
import com.example.githubuser.util.Helpers.validateNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl
@Inject constructor() : RemoteDataSource {

    override suspend fun getUsers(): RemoteSealed<List<UserModel>> = withContext(Dispatchers.IO) {
        try {
            val getUsers = ApiConfig.getApiService().getUsers()
            val userModel = ArrayList<UserModel>()
            when {
                getUsers.isEmpty() -> {
                    RemoteSealed.Error(CODE_EMPTY)
                }
                else -> {
                    for (user in getUsers) {
                        if (user.login.isNullOrEmpty()) continue
                        val userDetailResponse =
                            ApiConfig.getApiService().getUserDetail(user.login)
                        if (!userDetailResponse.convertToDomain().validateNull())
                            userModel.add(userDetailResponse.convertToDomain())
                        if (userModel.size == 10) break
                    }
                    RemoteSealed.Value(userModel)
                }
            }
        } catch (e: Throwable) {
            Log.d("TESTING_PURPOSE", "Exception ${e.message}")
            RemoteSealed.Error(e.message)
        }
    }

    override suspend fun getUsersByQuery(query: String?): RemoteSealed<List<UserModel>> =
        withContext(Dispatchers.IO) {
            try {
                val userModel = ArrayList<UserModel>()
                if (query.isNullOrEmpty()) {
                    getUsers()
                } else {
                    val getUsers = ApiConfig.getApiService().getUsersByQuery(query)
                    when {
                        getUsers.items.isEmpty() -> {
                            RemoteSealed.Error(CODE_EMPTY)
                        }
                        else -> {
                            for (user in getUsers.items) {
                                if (user.login.isNullOrEmpty()) continue
                                val userDetailResponse =
                                    ApiConfig.getApiService().getUserDetail(user.login)
                                userModel.add(userDetailResponse.convertToDomain())
                                if (userModel.size == 10) break
                        }
                        RemoteSealed.Value(userModel)
                    }
                }
            }
        } catch (e: Throwable) {
                Log.d("TESTING_PURPOSE", "Exception ${e.message}")
                RemoteSealed.Error(e.message)
        }
    }

    override suspend fun getFollowers(username: String): RemoteSealed<List<UserModel>> =
        withContext(Dispatchers.IO) {
            try {
                val getUsers = ApiConfig.getApiService().getUserFollowers(username)
                val userModel = ArrayList<UserModel>()
                when {
                    getUsers.isEmpty() -> {
                        RemoteSealed.Error(CODE_EMPTY)
                    }
                    else -> {
                        for (user in getUsers) {
                            if (user.login.isNullOrEmpty()) continue
                            val userDetailResponse =
                                ApiConfig.getApiService().getUserDetail(user.login)
                            userModel.add(userDetailResponse.convertToDomain())
                            if (userModel.size == 10) break
                    }
                    RemoteSealed.Value(userModel)
                }
            }
        } catch (e: Throwable) {
                Log.d("TESTING_PURPOSE", "Exception ${e.message}")
                RemoteSealed.Error(e.message)
        }
    }

    override suspend fun getFollowing(username: String): RemoteSealed<List<UserModel>> =
        withContext(Dispatchers.IO) {
            try {
                val getUsers = ApiConfig.getApiService().getUserFollowing(username)
                val userModel = ArrayList<UserModel>()
                when {
                    getUsers.isEmpty() -> {
                        RemoteSealed.Error(CODE_EMPTY)
                    }
                    else -> {
                        for (user in getUsers) {
                            if (user.login.isNullOrEmpty()) continue
                            val userDetailResponse =
                                ApiConfig.getApiService().getUserDetail(user.login)
                            userModel.add(userDetailResponse.convertToDomain())
                            if (userModel.size == 10) break
                    }
                    RemoteSealed.Value(userModel)
                }
            }
        } catch (e: Throwable) {
                Log.d("TESTING_PURPOSE", "Exception ${e.message}")
                RemoteSealed.Error(e.message)
        }
    }
}