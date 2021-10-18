package com.example.githubuser.view.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuser.util.FakeData
import com.example.githubuser.util.Helpers.convertToDomain
import com.example.githubuser.util.LiveDataTestUtil.getOrAwaitValue
import com.example.githubuser.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createViewModel() {
        detailViewModel = DetailViewModel()
    }

    @Test
    fun `get detail user normal`() {
        detailViewModel.setUserDetail(FakeData.fakeUser1.convertToDomain())
        val result = detailViewModel.userDetail.getOrAwaitValue()
        Assert.assertEquals(
            FakeData.fakeUser1.convertToDomain(),
            result
        )
    }

    @Test
    fun `get detail user empty`() {
        detailViewModel.setUserDetail(null)
        val result = detailViewModel.userDetail.getOrAwaitValue()
        Assert.assertEquals(
            FakeData.nullUserData,
            result
        )
    }

}