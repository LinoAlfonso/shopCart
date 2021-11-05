package com.lino.shopcart

import android.app.Application
import androidx.room.Room
import com.lino.shopcart.database.ShopCartMovieDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShopCartApplication:Application()