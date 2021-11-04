package com.lino.shopcart.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lino.shopcart.models.Movie


@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies():LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovies(movieEntity: List<Movie>)

    @Update
    suspend fun updateMovie(movieEntity: Movie)

    @Delete
    suspend fun deleteMovie(movieEntity: Movie)

    @Query("UPDATE movies SET countCart = :quantity , inShopCart = :statusInCart WHERE id = :movieId")
    suspend fun updateQuantityProductCart(movieId: Long, quantity: Int, statusInCart: Boolean)

    @Query("SELECT * FROM movies WHERE inShopCart = :statusInCart")
    fun getMoviesInCart(statusInCart:Boolean):LiveData<List<Movie>>
}