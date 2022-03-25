package com.rmyfactory.githubuser.view.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rmyfactory.githubuser.datasource.local.LocalSealed
import com.rmyfactory.githubuser.datasource.repository.FakeMainRepository
import com.rmyfactory.githubuser.util.DataConstant
import com.rmyfactory.githubuser.util.LiveDataTestUtil.getOrAwaitValue
import com.rmyfactory.githubuser.util.MainCoroutineRule
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