package com.lino.shopcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lino.shopcart.apiRetrofit.RetrofitAPIHelper
import com.lino.shopcart.models.detailMovie.MovieDetail
import com.lino.shopcart.models.response.MessageResponse
import com.lino.shopcart.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel :ViewModel(){
    private var apiMovie = RetrofitAPIHelper.getApiCalls()
    private val _detailMovie = MutableLiveData<MovieDetail>()
    val detailMovie: LiveData<MovieDetail> = _detailMovie
    private val _messageError = MutableLiveData<MessageResponse>()
    val messageError: LiveData<MessageResponse> = _messageError
    private val utils = Utils()
    val isLoading = MutableLiveData<Boolean>()

    fun getDetailMovie(iMovie:String){
        isLoading.value = true
        val call = apiMovie.getDetailMovie(iMovie)
        call.enqueue(object : Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                processFinished()
            }
            override fun onResponse(
                call: Call<MovieDetail>,
                response: Response<MovieDetail>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {movieDetail->
                        _detailMovie.value = movieDetail
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