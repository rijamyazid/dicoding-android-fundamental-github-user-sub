package com.example.githubuser.view.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuser.util.DataConstant
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
        detailViewModel.setUserDetail(DataConstant.fakeUser1)
        val result = detailViewModel.userDetail.getOrAwaitValue()
        Assert.assertEquals(
            DataConstant.fakeUser1,
            result
        )
    }

    @Test
    fun `get detail user empty`() {
        detailViewModel.setUserDetail(null)
        val result = detailViewModel.userDetail.getOrAwaitValue()
        Assert.assertEquals(
            DataConstant.nullDataUser,
            result
        )
    }

}