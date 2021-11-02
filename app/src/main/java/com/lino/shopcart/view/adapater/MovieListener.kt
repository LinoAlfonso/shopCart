package com.lino.shopcart.view.adapater

import com.lino.shopcart.models.Movie

interface MovieListener {
    fun onMovieClicked(movie: Movie,position : Int)
}