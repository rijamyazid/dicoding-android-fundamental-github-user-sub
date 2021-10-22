package com.example.githubuser.datasource.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.remote.FakeRemoteDataSource
import com.example.githubuser.util.DataConstant
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
        mainRepository = MainRepositoryImplMirror(remoteDataSource)
    }

    @Test
    fun `get list user normal`() {
        remoteDataSource.fakeUsers = DataConstant.listUsers
        val result = mainRepository.getUsers().getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(DataConstant.listUsers),
            result
        )
    }

    @Test
    fun `get list user empty`() {
        val result = mainRepository.getUsers().getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Error(CODE_EMPTY),
            result
        )
    }

    @Test
    fun `get list user by query normal`() {
        val query = "Query"
        remoteDataSource.fakeUserByQueryandUsername = DataConstant.fakeUserByQueryandUsername
        val result = mainRepository.getUsersByQuery(query).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(DataConstant.fakeUserByQueryandUsername[query]),
            result
        )
    }

    @Test
    fun `get list user by query empty`() {
        val query = "Query"
        val result = mainRepository.getUsersByQuery(query).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Error(CODE_EMPTY),
            result
        )
    }

    @Test
    fun `get list followers normal`() {
        val username = "Username1"
        remoteDataSource.fakeUserByQueryandUsername = DataConstant.fakeUserByQueryandUsername
        val result = mainRepository.getFollowers(username).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(DataConstant.fakeUserByQueryandUsername[username]),
            result
        )
    }

    @Test
    fun `get list followers empty`() {
        val username = "Username1"
        val result = mainRepository.getFollowers(username).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Error(CODE_EMPTY),
            result
        )
    }

    @Test
    fun `get list following normal`() {
        val username = "Username2"
        remoteDataSource.fakeUserByQueryandUsername = DataConstant.fakeUserByQueryandUsername
        val result = mainRepository.getFollowing(username).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(DataConstant.fakeUserByQueryandUsername[username]),
            result
        )
    }

    @Test
    fun `get list following empty`() {
        val username = "Username2"
        val result = mainRepository.getFollowing(username).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Error(CODE_EMPTY),
            result
        )
    }
}