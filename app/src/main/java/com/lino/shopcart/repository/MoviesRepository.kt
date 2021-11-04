package com.lino.shopcart.repository

import com.lino.shopcart.apiRetrofit.RetrofitInstance
import com.lino.shopcart.database.ShopCartMovieDataBase
import com.lino.shopcart.models.Movie

class MoviesRepository(
    val db :ShopCartMovieDataBase
) {
    suspend fun getMovies() =
        RetrofitInstance.apiMovie.getMoviesPopular()

    suspend fun saveMoviesDB(listMovies:List<Movie>) = db.movieDao().saveMovies(listMovies)

    suspend fun updateQuantityProduct(movieId: Long, quantity: Int, statusInCart:Boolean) = db.movieDao().updateQuantityProductCart(movieId,quantity,statusInCart)

    fun getMoviesSave() = db.movieDao().getAllMovies()

    fun getMoviesInCart(statusInCart:Boolean) = db.movieDao().getMoviesInCart(statusInCart)
}