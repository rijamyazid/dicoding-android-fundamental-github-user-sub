package com.example.githubuser.datasource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.githubuser.datasource.local.LocalSealed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("LeakingThis")
abstract class NetworkBoundResource<LocalData, RemoteData> {

    private val result = MediatorLiveData<LocalSealed<LocalData>>()
    private var localResult: LiveData<LocalData>
    private lateinit var remoteResult: LiveData<LocalSealed<RemoteData>>

    init {

        result.value = LocalSealed.Loading(true)
        localResult = fetchFromLocal()
        result.addSource(localResult) { localData ->
            if (shouldFetch(localData)) {
                result.removeSource(localResult)
                fetchNewData()
            } else {
                result.value = LocalSealed.Value(localData)
            }
        }

    }

    abstract fun fetchFromLocal(): LiveData<LocalData>

    abstract fun shouldFetch(localData: LocalData?): Boolean

    abstract fun fetchFromNetwork(): LiveData<LocalSealed<RemoteData>>

    abstract suspend fun saveToLocal(remoteData: RemoteData)

    private fun fetchNewData() {
        remoteResult = fetchFromNetwork()
        result.addSource(remoteResult) {
            when (it) {
                is LocalSealed.Value -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        saveToLocal(it.data)
                    }
                    result.addSource(localResult) { localData ->
                        result.value = LocalSealed.Value(localData)
                    }
                }
                is LocalSealed.Error -> {
                    result.addSource(localResult) { _ ->
                        result.value = LocalSealed.Error(it.message)
                    }
                }
            }

        }

    }

    fun asLiveData(): LiveData<LocalSealed<LocalData>> = result

}