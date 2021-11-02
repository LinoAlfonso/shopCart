package com.lino.shopcart.apiRetrofit

import com.lino.shopcart.models.detailMovie.MovieDetail
import com.lino.shopcart.models.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIClient {
    @GET("movie/popular?api_key=688d9278c62008d44a0b325ac09bb552&language=es-ES")
    fun getMoviesPopular(): Call<MoviesResponse>

    @GET("movie/{id_movie}?api_key=688d9278c62008d44a0b325ac09bb552&language=es-ES")
    fun getDetailMovie(@Path(value="id_movie") id_movie:String): Call<MovieDetail>

}