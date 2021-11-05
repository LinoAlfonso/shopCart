package com.lino.shopcart.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lino.shopcart.database.dao.MovieDao
import com.lino.shopcart.models.Movie

@Database(entities = arrayOf(Movie::class),version = 1)
abstract class ShopCartMovieDataBase: RoomDatabase() {
    abstract fun movieDao():MovieDao

}