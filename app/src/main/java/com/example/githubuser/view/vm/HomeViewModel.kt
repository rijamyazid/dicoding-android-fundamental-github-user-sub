package com.example.githubuser.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    fun getAllUsers(): LiveData<LocalSealed<List<UserModel>>> = repository.getAllUsers()

}