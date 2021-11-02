package com.lino.shopcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lino.shopcart.apiRetrofit.RetrofitAPIHelper
import com.lino.shopcart.models.MessageResponse
import com.lino.shopcart.models.Movie
import com.lino.shopcart.models.MoviesResponse
import com.lino.shopcart.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesPopularViewModel:ViewModel() {
    private var apiMovie = RetrofitAPIHelper.getApiCalls()
    val listMoviesPopular: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _messageError = MutableLiveData<MessageResponse>()
    val messageError: LiveData<MessageResponse> = _messageError
    private val utils = Utils()
    val isLoading = MutableLiveData<Boolean>()

    fun refreshGetMovies() {
        getListMoviesPopular()
    }

    fun getListMoviesPopular(){
        isLoading.value = true
        val call = apiMovie.getMoviesPopular()
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                processFinished()
            }
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {moviesResponse->
                        moviesResponse.listMovies.let {
                            listMoviesPopular.postValue(it)
                        }
                    }
                }else {
                    response.errorBody()?.let { errorBody ->
                        val errorBodyString = errorBody.string()
                        _messageError.value = utils.covertResponseMessage(errorBodyString)
                    }
                }
                processFinished()
            }

        })
    }

    fun processFinished(){
        isLoading.value = false
    }
}