package com.lino.shopcart.repository

import com.lino.shopcart.apiRetrofit.RetrofitInstance
import com.lino.shopcart.database.ShopCartMovieDataBase

class MoviesRepository(
    val db :ShopCartMovieDataBase
) {
    suspend fun getMovies() =
        RetrofitInstance.apiMovie.getMoviesPopular()
}