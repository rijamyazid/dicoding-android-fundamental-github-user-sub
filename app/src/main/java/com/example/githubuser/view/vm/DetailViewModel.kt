package com.example.githubuser.view.vm

import androidx.lifecycle.*
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.repository.MainRepository
import com.example.githubuser.util.DataConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(private var repository: MainRepository) : ViewModel() {

    private val _userDetail = MutableLiveData<UserModel>()
    val userDetail: LiveData<UserModel> get() = _userDetail.distinctUntilChanged()

    fun setUserDetail(userDetail: UserModel?) {
        _userDetail.postValue(userDetail ?: DataConstant.nullUserData)
    }

    private val _username = MutableLiveData<String>()
    fun setUsername(username: String) {
        _username.postValue(username)
    }

    val dataFollowers = _username.switchMap { getFollowers(it) }.distinctUntilChanged()
    fun getFollowers(username: String) = repository.getFollowers(username)
    val dataFollowing = _username.switchMap { getFollowing(it) }.distinctUntilChanged()
    fun getFollowing(username: String) = repository.getFollowing(username)

}