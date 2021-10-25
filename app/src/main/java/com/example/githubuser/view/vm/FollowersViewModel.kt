package com.example.githubuser.view.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import com.example.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _username = MutableLiveData<String>()
    fun setUsername(username: String) {
        if (_username.value != username)
            _username.postValue(username)
    }

    val dataFollowers = _username.switchMap { getFollowers(it) }.distinctUntilChanged()
    fun getFollowers(username: String) = repository.getFollowers(username)
}