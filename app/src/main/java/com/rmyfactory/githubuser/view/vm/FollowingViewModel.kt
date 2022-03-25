package com.rmyfactory.githubuser.view.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import com.rmyfactory.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _username = MutableLiveData<String>()
    fun setUsername(username: String) {
        if (_username.value != username)
            _username.postValue(username)
    }

    val dataFollowing = _username.switchMap { getFollowing(it) }.distinctUntilChanged()
    fun getFollowing(username: String) = repository.getFollowing(username)

}