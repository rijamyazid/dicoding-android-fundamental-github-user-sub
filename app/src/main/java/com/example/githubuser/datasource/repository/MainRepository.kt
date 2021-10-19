package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel

interface MainRepository {

    fun getUsers(): LiveData<LocalSealed<List<UserModel>>>
    fun getUsersByQuery(query: String?): LiveData<LocalSealed<List<UserModel>>>

}