package com.lino.shopcart.models.response

import com.google.gson.annotations.SerializedName
import com.lino.shopcart.models.Movie

data class MoviesResponse(
    @SerializedName("page")
    var page:Int,
    @SerializedName("results")
    var listMovies:List<Movie>
)
