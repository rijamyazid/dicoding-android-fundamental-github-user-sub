package com.example.githubuser.view.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.repository.FakeMainRepository
import com.example.githubuser.util.DataConstant
import com.example.githubuser.util.LiveDataTestUtil.getOrAwaitValue
import com.example.githubuser.util.MainCoroutineRule
import com.example.githubuser.util.NetworkConstant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    private lateinit var mainRepository: FakeMainRepository
    private lateinit var searchViewModel: SearchViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createViewModel() {
        mainRepository = FakeMainRepository()
        searchViewModel = SearchViewModel(mainRepository)
    }

    @Test
    fun `get list users by query normal`() {
        val query = "Query"
        mainRepository.fakeUserByQueryandUsername = DataConstant.fakeUserByQueryandUsername
        val result = searchViewModel.getUsersByQuery(query).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(DataConstant.fakeUserByQueryandUsername[query]),
            result
        )
    }

    @Test
    fun `get list users by query empty`() {
        val query = ""
        mainRepository.fakeListUsers = DataConstant.listUsers
        val result = searchViewModel.getUsersByQuery(query).getOrAwaitValue()
        Assert.assertEquals(
            LocalSealed.Value(DataConstant.listUsers),
            result
        )
    }

}