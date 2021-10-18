package com.example.githubuser.datasource.remote

import com.example.githubuser.datasource.remote.response.UserResponse
import com.example.githubuser.util.Helpers.CODE_EMPTY

class FakeRemoteDataSource(
    var fakeUsers: MutableList<UserResponse> = mutableListOf()
) : RemoteDataSource {

    override fun getAllUsers(): RemoteSealed<List<UserResponse>> {
        return if (fakeUsers.isEmpty()) RemoteSealed.Error(CODE_EMPTY)
        else RemoteSealed.Value(fakeUsers)
    }

}