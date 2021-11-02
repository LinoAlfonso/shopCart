package com.lino.shopcart.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page")
    var page:Int,
    @SerializedName("results")
    var listMovies:List<Movie>
)
