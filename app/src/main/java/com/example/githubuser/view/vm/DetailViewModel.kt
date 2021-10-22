package com.example.githubuser.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.util.DataConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor() : ViewModel() {

    private val _userDetail = MutableLiveData<UserModel>()
    val userDetail: LiveData<UserModel> get() = _userDetail.distinctUntilChanged()

    fun setUserDetail(userDetail: UserModel?) {
        _userDetail.postValue(userDetail ?: DataConstant.nullDataUser)
    }

}