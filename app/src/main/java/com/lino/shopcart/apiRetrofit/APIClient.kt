package com.lino.shopcart.apiRetrofit

import com.lino.shopcart.models.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIClient {
    @GET("movie/popular?api_key=688d9278c62008d44a0b325ac09bb552&language=es-ES")
    fun getMoviesPopular(): Call<MoviesResponse>
}