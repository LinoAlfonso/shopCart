package com.lino.shopcart.service

import com.lino.shopcart.models.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class APIService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getMoviesPopular():MoviesResponse{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(APIClient::class.java).getMoviesPopular()
            response.body()?: error("Lista vacia")
        }

    }
}