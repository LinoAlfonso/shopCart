package com.lino.shopcart.models
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "Movies"
)
data class Movie (
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @PrimaryKey
    @SerializedName("id")
    var id: Long,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("popularity")
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("video")
    var video: Boolean,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Long,
    var countCart: Int,
    var inShopCart:Boolean
):Serializable