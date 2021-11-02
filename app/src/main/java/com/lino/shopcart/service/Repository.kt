package com.lino.shopcart.service

import com.lino.shopcart.models.response.MoviesResponse

class Repository {
    private val api = APIService()

    private suspend fun getMoviesPopular(): MoviesResponse {
        val response = api.getMoviesPopular()
        return response
    }
}