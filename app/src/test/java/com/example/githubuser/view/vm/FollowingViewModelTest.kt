package com.example.githubuser.view.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.repository.FakeMainRepository
import com.example.githubuser.util.DataConstant
import com.example.githubuser.util.Helpers
import com.example.githubuser.util.LiveDataTestUtil.getOrAwaitValue
import com.example.githubuser.util.MainCoroutineRule
import com.example.githubuser.util.NetworkConstant.CODE_EMPTY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FollowingViewModelTest {

    private lateinit var mainRepository: FakeMainRepository
    private lateinit var followingViewModel: FollowingViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createViewModel() {
        mainRepository = FakeMainRepository()
        followingViewModel = FollowingViewModel(mainRepository)
    }

    @Test
    fun `get followers normal`() {
        val username = "Username2"
        mainRepository.fakeUserByQueryandUsername = DataConstant.fakeUserByQueryandUsername
        val result = followingViewModel.getFollowing(username).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(DataConstant.fakeUserByQueryandUsername[username]),
            result
        )
    }

    @Test
    fun `get followers empty`() {
        val username = "Username1"
        val result = followingViewModel.getFollowing(username).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Error(CODE_EMPTY),
            result
        )
    }

}