package com.lino.shopcart.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lino.shopcart.database.dao.MovieDao
import com.lino.shopcart.database.entity.MovieEntity

@Database(entities = arrayOf(MovieEntity::class),version = 1)
abstract class ShopCartMovieDataBase: RoomDatabase() {
    abstract fun movieDao():MovieDao
}