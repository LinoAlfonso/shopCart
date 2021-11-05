package com.lino.shopcart.repository

import com.lino.shopcart.di.APIClient
import com.lino.shopcart.database.ShopCartMovieDataBase
import com.lino.shopcart.database.dao.MovieDao
import com.lino.shopcart.models.Movie
import javax.inject.Inject

class MoviesRepository @Inject constructor(val db :MovieDao, private val apiMovie: APIClient
) {
    suspend fun getMovies() = apiMovie.getMoviesPopular()

    suspend fun saveMoviesDB(listMovies:List<Movie>) = db.saveMovies(listMovies)

    suspend fun updateQuantityProduct(movieId: Long, quantity: Int, statusInCart:Boolean) = db.updateQuantityProductCart(movieId,quantity,statusInCart)

    fun getMoviesSave() = db.getAllMovies()

    fun getMoviesInCart(statusInCart:Boolean) = db.getMoviesInCart(statusInCart)
}