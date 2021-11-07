package com.lino.shopcart.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lino.shopcart.database.ShopCartMovieDataBase
import com.lino.shopcart.database.dao.MovieDao
import com.lino.shopcart.models.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShopCartMovieDataBase
    private lateinit var dao: MovieDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShopCartMovieDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = database.movieDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertMovieItem() = runBlockingTest {
        var list = mutableListOf<Movie>()
        list.add(2, Movie(
            false, "", 23, "es", "test",
            "tests", 5.6, "", "", "test",
            true, 4.5, 12, 12,true
        ))
        dao.saveMovies(list)
    }

    @Test
    fun getMovies() = runBlockingTest {
        dao.getAllMovies()
    }


}