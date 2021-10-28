package com.example.githubuser.view.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuser.datasource.repository.FakeMainRepository
import com.example.githubuser.util.DataConstant
import com.example.githubuser.util.LiveDataTestUtil.getOrAwaitValue
import com.example.githubuser.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteViewModelTest {

    private lateinit var repository: FakeMainRepository
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instanceExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createViewModel() {
        repository = FakeMainRepository()
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun `get favourite users`() {
        repository.fakeListUsers = DataConstant.listUsers
        val result = viewModel.getFavoriteUsers().getOrAwaitValue()
        Assert.assertEquals(
            DataConstant.listUsers,
            result
        )
    }

}