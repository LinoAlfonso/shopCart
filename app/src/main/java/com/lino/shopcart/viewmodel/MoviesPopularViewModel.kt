package com.lino.shopcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lino.shopcart.apiRetrofit.RetrofitAPIHelper
import com.lino.shopcart.models.response.MessageResponse
import com.lino.shopcart.models.Movie
import com.lino.shopcart.models.response.MoviesResponse
import com.lino.shopcart.repository.MoviesRepository
import com.lino.shopcart.utils.Resource
import com.lino.shopcart.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesPopularViewModel(
    val moviesRepository: MoviesRepository
):ViewModel() {

    val moviesPopular: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    fun refreshGetMovies() {
        getMoviesPopular()
    }

    fun getMoviesPopular() = viewModelScope.launch {
        moviesPopular.postValue(Resource.Loading())
        val response = moviesRepository.getMovies()
        moviesPopular.postValue(handleMoviesResponse(response))
    }

    private fun handleMoviesResponse(response: Response<MoviesResponse>):Resource<MoviesResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                saveMoviesDB(resultResponse.listMovies)
                return  Resource.Success(resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }

    fun saveMoviesDB(listMovies:List<Movie>) = viewModelScope.launch {
        moviesRepository.saveMoviesDB(listMovies)
    }

    suspend fun updateQuantityProduct(idMovie:Long,quantity:Int,statusInCart:Boolean) = viewModelScope.launch {
        moviesRepository.updateQuantityProduct(idMovie,quantity,statusInCart)
    }

    fun  getAllMoviesSave() = moviesRepository.getMoviesSave()

    fun  getMoviesInCartSave() = moviesRepository.getMoviesInCart(true)




}