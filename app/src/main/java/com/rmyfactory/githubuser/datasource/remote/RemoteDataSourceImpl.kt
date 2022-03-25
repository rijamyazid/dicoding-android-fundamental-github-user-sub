package com.rmyfactory.githubuser.datasource.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.rmyfactory.githubuser.datasource.local.LocalSealed
import com.rmyfactory.githubuser.datasource.local.model.UserModel
import com.rmyfactory.githubuser.datasource.remote.api.ApiConfig
import com.rmyfactory.githubuser.util.EspressoIdlingResource
import com.rmyfactory.githubuser.util.Helpers.convertToDomain
import com.rmyfactory.githubuser.util.Helpers.validateNull
import com.rmyfactory.githubuser.util.NetworkConstant.CODE_EMPTY
import com.rmyfactory.githubuser.util.NetworkConstant.testingLog
import javax.inject.Inject

class RemoteDataSourceImpl
@Inject constructor() : RemoteDataSource {

    override fun getUsers(): LiveData<LocalSealed<List<UserModel>>> =
        liveData {
            try {
                EspressoIdlingResource.increment()
                testingLog("getUsers: Loading")
                emit(LocalSealed.Loading(true))
                testingLog("getUsers: After emit")
                val userModel = ArrayList<UserModel>()
                testingLog("getUsers: user model")
                val getUsers = ApiConfig.getApiService().getUsers()
                testingLog("getUsers: After data fetch: $getUsers")
                when {
                    getUsers.isEmpty() -> {
                        testingLog("getUsers: Empty")
                        EspressoIdlingResource.decrement()
                        emit(LocalSealed.Error(CODE_EMPTY))
                    }
                    else -> {
                        for (user in getUsers) {
                            if (user.login.isNullOrEmpty()) continue
                            testingLog("getUsers: Value - item")
                            val userDetailResponse =
                                ApiConfig.getApiService().getUserDetail(user.login)
                            if (!userDetailResponse.convertToDomain().validateNull())
                                userModel.add(userDetailResponse.convertToDomain())
                            if (userModel.size == 10) break
                        }
                        testingLog("getUsers: Value")
                        EspressoIdlingResource.decrement()
                        emit(LocalSealed.Value(userModel))
                    }
                }
            } catch (e: Throwable) {
                testingLog("getUsers: Error")
                Log.d("TESTING_PURPOSE", "Exception ${e.message}")
                EspressoIdlingResource.decrement()
                emit(LocalSealed.Error(e.message))
            }
        }

    override fun getUsersByQuery(query: String): LiveData<LocalSealed<List<UserModel>>> =
        liveData {
            try {
                EspressoIdlingResource.increment()
                emit(LocalSealed.Loading(true))
                val userModel = ArrayList<UserModel>()
                val getUsers = ApiConfig.getApiService().getUsersByQuery(query)
                when {
                    getUsers.items.isEmpty() -> {
                        EspressoIdlingResource.decrement()
                        emit(LocalSealed.Error(CODE_EMPTY))
                    }
                    else -> {
                        for (user in getUsers.items) {
                            if (user.login.isNullOrEmpty()) continue
                            val userDetailResponse =
                                ApiConfig.getApiService().getUserDetail(user.login)
                            userModel.add(userDetailResponse.convertToDomain())
                            if (userModel.size == 10) break
                        }
                        EspressoIdlingResource.decrement()
                        emit(LocalSealed.Value(userModel))
                    }
                }
            } catch (e: Throwable) {
                Log.d("TESTING_PURPOSE", "Exception ${e.message}")
                EspressoIdlingResource.decrement()
                emit(LocalSealed.Error(e.message))
            }
        }

    override fun getFollowers(username: String): LiveData<LocalSealed<List<UserModel>>> =
        liveData {
            try {
                EspressoIdlingResource.increment()
                emit(LocalSealed.Loading(true))
                val getUsers = ApiConfig.getApiService().getUserFollowers(username)
                val userModel = ArrayList<UserModel>()
                when {
                    getUsers.isEmpty() -> {
                        EspressoIdlingResource.decrement()
                        emit(LocalSealed.Error(CODE_EMPTY))
                    }
                    else -> {
                        for (user in getUsers) {
                            if (user.login.isNullOrEmpty()) continue
                            val userDetailResponse =
                                ApiConfig.getApiService().getUserDetail(user.login)
                            userModel.add(userDetailResponse.convertToDomain())
                            if (userModel.size == 10) break
                        }
                        EspressoIdlingResource.decrement()
                        emit(LocalSealed.Value(userModel))
                    }
                }
            } catch (e: Throwable) {
                Log.d("TESTING_PURPOSE", "Exception ${e.message}")
                EspressoIdlingResource.decrement()
                emit(LocalSealed.Error(e.message))
            }
        }

    override fun getFollowing(username: String): LiveData<LocalSealed<List<UserModel>>> =
        liveData {
            try {
                EspressoIdlingResource.increment()
                emit(LocalSealed.Loading(true))
                val getUsers = ApiConfig.getApiService().getUserFollowing(username)
                val userModel = ArrayList<UserModel>()
                when {
                    getUsers.isEmpty() -> {
                        EspressoIdlingResource.decrement()
                        emit(LocalSealed.Error(CODE_EMPTY))
                    }
                    else -> {
                        for (user in getUsers) {
                            if (user.login.isNullOrEmpty()) continue
                            val userDetailResponse =
                                ApiConfig.getApiService().getUserDetail(user.login)
                            userModel.add(userDetailResponse.convertToDomain())
                            if (userModel.size == 10) break
                        }
                        EspressoIdlingResource.decrement()
                        emit(LocalSealed.Value(userModel))
                    }
                }
            } catch (e: Throwable) {
                Log.d("TESTING_PURPOSE", "Exception ${e.message}")
                EspressoIdlingResource.decrement()
                emit(LocalSealed.Error(e.message))
            }
        }
}