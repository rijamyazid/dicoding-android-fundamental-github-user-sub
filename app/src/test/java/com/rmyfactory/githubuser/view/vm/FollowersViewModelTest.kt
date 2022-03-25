package com.rmyfactory.githubuser.view.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rmyfactory.githubuser.datasource.local.LocalSealed
import com.rmyfactory.githubuser.datasource.repository.FakeMainRepository
import com.rmyfactory.githubuser.util.DataConstant.fakeUserByQueryandUsername
import com.rmyfactory.githubuser.util.LiveDataTestUtil.getOrAwaitValue
import com.rmyfactory.githubuser.util.MainCoroutineRule
import com.rmyfactory.githubuser.util.NetworkConstant.CODE_EMPTY
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