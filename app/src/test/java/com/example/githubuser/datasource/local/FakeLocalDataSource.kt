package com.example.githubuser.datasource.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.githubuser.datasource.local.model.UserModel

class FakeLocalDataSource(
    var fakeListUsers: MutableList<UserModel> = mutableListOf(),
    var fakeMapUsers: Map<String, UserModel> = mapOf()
) : LocalDataSource {
    override fun readUsers(): LiveData<List<UserModel>> = liveData {
        emit(fakeListUsers)
    }

    override fun readUser(username: String): LiveData<UserModel> = liveData {
        emit(fakeMapUsers[username]!!)
    }

    override fun readFavoriteUsers(): LiveData<List<UserModel>> = liveData {
        emit(fakeListUsers)
    }

    override suspend fun updateUser(user: UserModel) {}

    override suspend fun insertUser(user: UserModel) {}

    override suspend fun insertUsers(users: List<UserModel>) {}
}