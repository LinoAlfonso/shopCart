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
   /* companion object{
        @Volatile
        private var instance : ShopCartMovieDataBase? = null
        private var LOCK = Any()

        operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
                instance ?: createDatabase(context).also {
                    instance = it
                }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ShopCartMovieDataBase::class.java,
            "shopCartMovie_db.db"
        ).build()
    }*/
}