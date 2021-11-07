package com.lino.repository

import androidx.lifecycle.LiveData
import com.lino.shopcart.models.Movie

interface RepositoryTest {

    suspend fun insertMovieItem(item: Movie)

    suspend fun deleteMovieItem(item: Movie)

    fun observeAllMovieItems(): LiveData<List<Movie>>


}