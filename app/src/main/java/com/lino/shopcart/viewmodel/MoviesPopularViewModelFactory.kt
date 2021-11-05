package com.lino.shopcart.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lino.shopcart.repository.MoviesRepository

class MoviesPopularViewModelFactory (val app:Application,val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesPopularViewModel(app,moviesRepository) as T
    }

}