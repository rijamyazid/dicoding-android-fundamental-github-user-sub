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

class DetailViewModelTest {

    private lateinit var mainRepository: FakeMainRepository
    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createViewModel() {
        mainRepository = FakeMainRepository()
        detailViewModel = DetailViewModel(mainRepository)
    }

    @Test
    fun `get detail user`() {
        val username = "mojombo"
        mainRepository.fakeMapUsers = DataConstant.mapUsers
        val result = detailViewModel.getUser(username).getOrAwaitValue()
        Assert.assertEquals(
            DataConstant.fakeUser1,
            result
        )
    }

}