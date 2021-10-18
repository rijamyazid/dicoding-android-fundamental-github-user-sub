package com.example.githubuser.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.util.FakeData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor() : ViewModel() {

    private val _userDetail = MutableLiveData<UserModel>()
    val userDetail: LiveData<UserModel> get() = _userDetail

    fun setUserDetail(userDetail: UserModel?) {
        _userDetail.postValue(userDetail ?: FakeData.nullUserData)
    }

}