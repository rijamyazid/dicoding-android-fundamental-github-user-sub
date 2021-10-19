package com.example.githubuser.view.vm

import android.util.Log
import androidx.lifecycle.*
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    val dataUsers = getUsers().distinctUntilChanged()
    fun getUsers(): LiveData<LocalSealed<List<UserModel>>> {
        Log.d("TESTING_PURPOSE", "0")
        return repository.getUsers()
    }

    val query = MutableLiveData<String>()
    fun setQuery(query: String) {
        this.query.postValue(query)
    }

    val dataUsersByQuery = query.switchMap { getUsersByQuery(it) }.distinctUntilChanged()
    fun getUsersByQuery(query: String) = repository.getUsersByQuery(query)

}