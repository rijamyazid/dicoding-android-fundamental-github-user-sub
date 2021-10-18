package com.example.githubuser.datasource.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.remote.FakeRemoteDataSource
import com.example.githubuser.util.FakeData
import com.example.githubuser.util.Helpers.CODE_EMPTY
import com.example.githubuser.util.LiveDataTestUtil.getOrAwaitValue
import com.example.githubuser.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainRepositoryImplTest {

    private lateinit var remoteDataSource: FakeRemoteDataSource

    private lateinit var mainRepository: MainRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createRepository() {
        remoteDataSource = FakeRemoteDataSource()
        mainRepository = MainRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `get list users normal`() {
        remoteDataSource.fakeUsers = FakeData.listUserRemote
        val result = mainRepository.getAllUsers().getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(FakeData.listUserLocal),
            result
        )
    }

    @Test
    fun `get list users empty`() {
        remoteDataSource.fakeUsers = mutableListOf()
        val result = mainRepository.getAllUsers().getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Error(CODE_EMPTY),
            result
        )
    }

}