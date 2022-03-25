package com.rmyfactory.githubuser.view.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import com.rmyfactory.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val refreshTrigger = MutableLiveData(Unit)

    fun refresh() {
        refreshTrigger.postValue(Unit)
    }

    val dataUsers = refreshTrigger.switchMap { getUsers() }.distinctUntilChanged()
    fun getUsers() = repository.getUsers()

}