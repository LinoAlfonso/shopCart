package com.lino.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.ExpectFailure.assertThat
import com.lino.MainCoroutineRule
import com.lino.repository.FakeRepository
import com.lino.shopcart.ShopCartApplication
import com.lino.shopcart.viewmodel.MoviesPopularViewModel
import com.google.common.truth.Truth.assertThat
import com.lino.shopcart.models.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MoviesPopularViewModel

    @Before
    fun setup() {
        //viewModel = MoviesPopularViewModel(,poFakeRepository())
    }

    @Test
    fun `insert movie item with empty field, returns error`() {

    }
}