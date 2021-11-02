package com.lino.shopcart.service

import com.lino.shopcart.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIClient {

    @GET("movie/popular?api_key=688d9278c62008d44a0b325ac09bb552&language=es-ES")
    suspend fun getMoviesPopular(): Response<MoviesResponse>
}