package com.lino.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lino.shopcart.models.Movie

class FakeRepository : RepositoryTest {
    private val moviesItems = mutableListOf<Movie>()

    private val observableMoviesItems = MutableLiveData<List<Movie>>(moviesItems)


    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableMoviesItems.postValue(moviesItems)

    }


    override suspend fun insertMovieItem(item: Movie) {
        moviesItems.add(3,item)
        refreshLiveData()
    }

    override suspend fun deleteMovieItem(item: Movie) {

    }

    override fun observeAllMovieItems(): LiveData<List<Movie>> {
        return observableMoviesItems
    }
}