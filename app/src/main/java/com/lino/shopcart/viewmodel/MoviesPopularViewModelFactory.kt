package com.lino.shopcart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lino.shopcart.repository.MoviesRepository

class MoviesPopularViewModelFactory (val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesPopularViewModel(moviesRepository) as T
    }


}