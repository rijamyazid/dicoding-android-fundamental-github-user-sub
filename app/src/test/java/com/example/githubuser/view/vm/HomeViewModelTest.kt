package com.example.githubuser.view.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.repository.FakeMainRepository
import com.example.githubuser.util.DataConstant
import com.example.githubuser.util.Helpers.CODE_EMPTY
import com.example.githubuser.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var mainRepository: FakeMainRepository

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createViewModel() {
        mainRepository = FakeMainRepository()
        homeViewModel = HomeViewModel(mainRepository)
    }

    @Test
    fun `get list users normal`() {
        mainRepository.fakeUsers = DataConstant.listUserLocal
        val result = homeViewModel.getAllUsers().getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(DataConstant.listUserLocal),
            result
        )
    }

    @Test
    fun `get list users empty`() {
        mainRepository.fakeUsers = mutableListOf()
        val result = homeViewModel.getAllUsers().getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Error(CODE_EMPTY),
            result
        )
    }

}