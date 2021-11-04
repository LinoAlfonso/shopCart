package com.lino.shopcart.view.adapater

import com.lino.shopcart.models.Movie

interface ShopCartListener {
    fun clickListenerMovie(movie: Movie,typeClick:Int)
}