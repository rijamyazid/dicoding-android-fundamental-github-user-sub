package com.example.githubuser.datasource.remote

import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.util.Helpers.CODE_EMPTY

class FakeRemoteDataSource(
    var fakeUsers: MutableList<UserModel> = mutableListOf(),
    var fakeUserByQueryandUsername: Map<String, List<UserModel>> = mutableMapOf()
) : RemoteDataSource {

    override suspend fun getUsers(): RemoteSealed<List<UserModel>> {
        return if (fakeUsers.isEmpty()) RemoteSealed.Error(CODE_EMPTY)
        else RemoteSealed.Value(fakeUsers)
    }

    override suspend fun getUsersByQuery(query: String?): RemoteSealed<List<UserModel>> {
        return if (fakeUserByQueryandUsername.isEmpty()) RemoteSealed.Error(CODE_EMPTY)
        else RemoteSealed.Value(fakeUserByQueryandUsername[query]!!)
    }

    override suspend fun getFollowers(username: String): RemoteSealed<List<UserModel>> {
        return if (fakeUserByQueryandUsername.isEmpty()) RemoteSealed.Error(CODE_EMPTY)
        else RemoteSealed.Value(fakeUserByQueryandUsername[username]!!)
    }

    override suspend fun getFollowing(username: String): RemoteSealed<List<UserModel>> {
        return if (fakeUserByQueryandUsername.isEmpty()) RemoteSealed.Error(CODE_EMPTY)
        else RemoteSealed.Value(fakeUserByQueryandUsername[username]!!)
    }

}