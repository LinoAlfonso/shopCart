package com.lino.shopcart

import android.app.Application
import androidx.room.Room
import com.lino.shopcart.database.ShopCartMovieDataBase

class ShopCartApplication:Application() {
    companion object{
        lateinit var dataBase : ShopCartMovieDataBase
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(this,
            ShopCartMovieDataBase::class.java,"ShopCartDatabase").build()
    }
}