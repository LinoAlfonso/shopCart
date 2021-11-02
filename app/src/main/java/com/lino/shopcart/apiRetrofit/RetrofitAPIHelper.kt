package com.lino.shopcart.apiRetrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitAPIHelper {

    val url= "https://api.themoviedb.org/3/"

    private var retrofit: Retrofit? = null

    fun getClient(baseUrl : String) : Retrofit? {
        val client = OkHttpClient.Builder().
        connectTimeout(200, TimeUnit.SECONDS).
        readTimeout(200, TimeUnit.SECONDS).build()
        if(retrofit==null){
            retrofit = Retrofit.Builder().
            baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun getApiCalls() : APIClient{
        return getClient(url)!!.create(APIClient::class.java)
    }


}