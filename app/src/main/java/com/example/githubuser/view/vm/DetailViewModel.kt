package com.example.githubuser.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.datasource.remote.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor() : ViewModel() {

    private val _userDetail = MutableLiveData<UserResponse>()
    val userDetail: LiveData<UserResponse> get() = _userDetail

    fun setUserDetail(userDetail: UserResponse) {
        _userDetail.postValue(userDetail)
    }

}