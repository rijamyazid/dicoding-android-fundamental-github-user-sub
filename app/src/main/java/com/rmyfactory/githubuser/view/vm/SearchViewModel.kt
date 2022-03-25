package com.rmyfactory.githubuser.view.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import com.rmyfactory.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    fun refresh(query: String? = this.query.value) {
        this.query.postValue(query)
    }

    private val query = MutableLiveData<String>()
    fun setQuery(query: String?) {
        if (query != this.query.value)
            this.query.postValue(query)
    }

    val dataUsersByQuery = query.switchMap { getUsersByQuery(it) }.distinctUntilChanged()
    fun getUsersByQuery(query: String?) = repository.getUsersByQuery(query)

}