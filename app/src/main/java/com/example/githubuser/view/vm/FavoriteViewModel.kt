package com.example.githubuser.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    val dataFavoriteUsers = getFavoriteUsers().distinctUntilChanged()
    fun getFavoriteUsers(): LiveData<List<UserModel>> = repository.getFavoriteUsers()

}