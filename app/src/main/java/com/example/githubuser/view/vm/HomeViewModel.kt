package com.example.githubuser.view.vm

import androidx.lifecycle.ViewModel
import com.example.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(repository: MainRepository) : ViewModel() {

    val getUsers = repository.getAllUsers()

}