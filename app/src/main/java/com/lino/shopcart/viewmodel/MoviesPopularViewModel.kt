package com.lino.shopcart.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.*
import com.lino.shopcart.ShopCartApplication
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
import java.io.IOException

class MoviesPopularViewModel(
    app:Application,
    val moviesRepository: MoviesRepository
):AndroidViewModel(app) {

    val moviesPopular: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    fun refreshGetMovies() {
        getMoviesPopular()
    }

    fun getMoviesPopular() = viewModelScope.launch {
     /*   moviesPopular.postValue(Resource.Loading())
        val response = moviesRepository.getMovies()
        moviesPopular.postValue(handleMoviesResponse(response))*/
        safeGetMoviesPopular()
    }

    private suspend fun safeGetMoviesPopular(){
        moviesPopular.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()){
                val response = moviesRepository.getMovies()
                moviesPopular.postValue(handleMoviesResponse(response))
            }else{
                moviesPopular.postValue(Resource.Error(message = "No tienes acceso a internet"))
            }
        }catch (t:Throwable){
            when(t){
                is IOException ->  moviesPopular.postValue(Resource.Error(message = "Error en la red"))
                else ->  moviesPopular.postValue(Resource.Error(message = "Error al obtener los datos"))
            }
        }
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

    private fun hasInternetConnection():Boolean{
        val connectivityManager = getApplication<ShopCartApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as  ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}