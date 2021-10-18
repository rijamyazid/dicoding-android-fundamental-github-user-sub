package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.util.Helpers.CODE_EMPTY

class FakeMainRepository(
    var fakeUsers: MutableList<UserModel> = mutableListOf()
) : MainRepository {

    override fun getAllUsers(): LiveData<LocalSealed<List<UserModel>>> {
        return liveData {
            emit(
                if (fakeUsers.isEmpty()) LocalSealed.Error(CODE_EMPTY)
                else LocalSealed.Value(fakeUsers)
            )
        }
    }

}