package com.lino.shopcart.models.detailMovie

import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
)
