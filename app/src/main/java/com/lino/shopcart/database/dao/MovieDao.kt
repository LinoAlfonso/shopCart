package com.lino.shopcart.database.dao

import androidx.room.*
import com.lino.shopcart.database.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity")
    fun getAllMovies():MutableList<MovieEntity>

    @Insert
    fun addMovie(movieEntity: MovieEntity)

    @Update
    fun updateMovie(movieEntity: MovieEntity)

    @Delete
    fun deleteMovie(movieEntity: MovieEntity)
}