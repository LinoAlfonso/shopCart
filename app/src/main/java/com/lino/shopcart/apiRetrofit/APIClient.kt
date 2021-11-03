package com.lino.shopcart.apiRetrofit

import com.lino.shopcart.models.detailMovie.MovieDetail
import com.lino.shopcart.models.detailMovie.SpokenLanguage
import com.lino.shopcart.models.response.MoviesResponse
import com.lino.shopcart.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIClient {

    @GET("movie/popular?")
    suspend fun getMoviesPopular(
        @Query("api_key") apiKey:String = API_KEY,
        @Query("language") language: String = "es-ES"
    ):Response<MoviesResponse>

    @GET("movie/{id_movie}?api_key=688d9278c62008d44a0b325ac09bb552&language=es-ES")
    fun getDetailMovie(@Path(value="id_movie") id_movie:String): Call<MovieDetail>

}