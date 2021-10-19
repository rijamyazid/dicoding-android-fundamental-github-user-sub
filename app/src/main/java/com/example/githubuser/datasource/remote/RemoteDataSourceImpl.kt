package com.example.githubuser.datasource.remote

import android.util.Log
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.remote.api.ApiConfig
import com.example.githubuser.util.Helpers.CODE_EMPTY
import com.example.githubuser.util.Helpers.convertToDomain
import com.example.githubuser.util.Helpers.validateNull
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataSourceImpl
@Inject constructor() : RemoteDataSource {

    override suspend fun getUsers(): RemoteSealed<List<UserModel>> {
        try {
            val getUsers = ApiConfig.getApiService().getUsers()
            val userModel = ArrayList<UserModel>()
            return when {
                getUsers.isEmpty() -> {
                    RemoteSealed.Error(CODE_EMPTY)
                }
                else -> {
                    for (user in getUsers) {
                        if (user.login.isNullOrEmpty()) RemoteSealed.Error(CODE_EMPTY)
                        val userDetailResponse =
                            ApiConfig.getApiService().getUserDetail(user.login!!)
                        if (!userDetailResponse.convertToDomain().validateNull())
                            userModel.add(userDetailResponse.convertToDomain())
                        if (userModel.size == 10) break
                    }
                    RemoteSealed.Value(userModel)
                }
            }
        } catch (e: HttpException) {
            Log.d("TESTING_PURPOSE", "Exception ${e.message}")
            return RemoteSealed.Error(CODE_EMPTY)
        } catch (e: Throwable) {
            Log.d("TESTING_PURPOSE", "Exception ${e.message}")
            return RemoteSealed.Error(CODE_EMPTY)
        }
    }

    override suspend fun getUsersByQuery(query: String): RemoteSealed<List<UserModel>> {
        val getUsers = ApiConfig.getApiService().getUsersByQuery(query)
        val userModel = ArrayList<UserModel>()
        return when {
            getUsers.items.isEmpty() -> {
                RemoteSealed.Error(CODE_EMPTY)
            }
            else -> {
                for (user in getUsers.items) {
                    if (user.login.isNullOrEmpty()) RemoteSealed.Error(CODE_EMPTY)
                    val userDetailResponse = ApiConfig.getApiService().getUserDetail(user.login!!)
                    userModel.add(userDetailResponse.convertToDomain())
                    if (userModel.size == 10) break
                }
                RemoteSealed.Value(userModel)
            }
        }
    }
}