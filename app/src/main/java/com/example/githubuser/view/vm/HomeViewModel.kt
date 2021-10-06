package com.example.githubuser.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.datasource.remote.RemoteSealed
import com.example.githubuser.datasource.remote.response.UserResponse
import com.example.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    fun getAllUsers(): LiveData<RemoteSealed<List<UserResponse>>> = repository.getAllUsers()

}