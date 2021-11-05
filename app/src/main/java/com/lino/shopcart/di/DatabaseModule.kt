package com.lino.shopcart.di

import android.content.Context
import androidx.room.Room
import com.lino.shopcart.database.ShopCartMovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        ShopCartMovieDataBase::class.java,
        "shopCartMovie_db.db"
    ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: ShopCartMovieDataBase) = db.movieDao()
}