package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.util.Helpers.CODE_EMPTY

class FakeMainRepository(
    var fakeUsers: MutableList<UserModel> = mutableListOf(),
    var fakeUserByQueryandUsername: Map<String, List<UserModel>> = mutableMapOf()
) : MainRepository {

    override fun getUsers(): LiveData<LocalSealed<List<UserModel>>> = liveData {
        emit(
            if (fakeUsers.isEmpty()) LocalSealed.Error(CODE_EMPTY)
            else LocalSealed.Value(fakeUsers)
        )
    }

    override fun getUsersByQuery(query: String?): LiveData<LocalSealed<List<UserModel>>> =
        liveData {
            emit(
                if (fakeUserByQueryandUsername.isEmpty()) LocalSealed.Error(CODE_EMPTY)
                else LocalSealed.Value(fakeUserByQueryandUsername[query]!!)
            )
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

}