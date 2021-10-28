package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.util.NetworkConstant.CODE_EMPTY

class FakeMainRepository(
    var fakeListUsers: MutableList<UserModel> = mutableListOf(),
    var fakeMapUsers: Map<String, UserModel> = mutableMapOf(),
    var fakeUserByQueryandUsername: Map<String, List<UserModel>> = mutableMapOf()
) : MainRepository {

    override fun getUsers(): LiveData<LocalSealed<List<UserModel>>> = liveData {
        emit(
            if (fakeListUsers.isEmpty()) LocalSealed.Error(CODE_EMPTY)
            else LocalSealed.Value(fakeListUsers)
        )
    }

    override fun getUser(username: String): LiveData<UserModel> = liveData {
        emit(fakeMapUsers[username]!!)
    }

    override fun getUsersByQuery(query: String?): LiveData<LocalSealed<List<UserModel>>> =
        liveData {
            emit(
                if (query.isNullOrEmpty()) LocalSealed.Value(fakeListUsers)
                else LocalSealed.Value(fakeUserByQueryandUsername[query]!!)
            )
        }

    override fun getFavoriteUsers(): LiveData<List<UserModel>> = liveData {
        emit(fakeListUsers)
    }

    override fun getFollowers(username: String): LiveData<LocalSealed<List<UserModel>>> = liveData {
        emit(
            if (fakeUserByQueryandUsername.isEmpty()) LocalSealed.Error(CODE_EMPTY)
            else LocalSealed.Value(fakeUserByQueryandUsername[username]!!)
        )
    }

    override fun getFollowing(username: String): LiveData<LocalSealed<List<UserModel>>> = liveData {
        emit(
            if (fakeUserByQueryandUsername.isEmpty()) LocalSealed.Error(CODE_EMPTY)
            else LocalSealed.Value(fakeUserByQueryandUsername[username]!!)
        )
    }

    override suspend fun updateUser(user: UserModel) {
    }

    override suspend fun insertUser(user: UserModel) {
    }

}