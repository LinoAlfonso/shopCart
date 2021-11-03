package com.lino.shopcart.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lino.shopcart.models.Movie


@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies():LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieEntity: Movie)

    @Update
    suspend fun updateMovie(movieEntity: Movie)

    @Delete
    suspend fun deleteMovie(movieEntity: Movie)
}