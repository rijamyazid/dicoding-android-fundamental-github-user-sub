package com.example.githubuser.view.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.repository.FakeMainRepository
import com.example.githubuser.util.DataConstant.fakeUserByQueryandUsername
import com.example.githubuser.util.Helpers.CODE_EMPTY
import com.example.githubuser.util.LiveDataTestUtil.getOrAwaitValue
import com.example.githubuser.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FollowersViewModelTest {

    private lateinit var mainRepository: FakeMainRepository
    private lateinit var followersViewModel: FollowersViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createViewModel() {
        mainRepository = FakeMainRepository()
        followersViewModel = FollowersViewModel(mainRepository)
    }

    @Test
    fun `get followers normal`() {
        val username = "Username1"
        mainRepository.fakeUserByQueryandUsername = fakeUserByQueryandUsername
        val result = followersViewModel.getFollowers(username).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(fakeUserByQueryandUsername[username]),
            result
        )
    }

    @Test
    fun `get followers empty`() {
        val username = "Username1"
        val result = followersViewModel.getFollowers(username).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Error(CODE_EMPTY),
            result
        )
    }

}