package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel

interface MainRepository {

    fun getAllUsers(): LiveData<LocalSealed<List<UserModel>>>

}