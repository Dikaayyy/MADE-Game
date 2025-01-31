package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.local.LocalDataSource
import com.example.core.data.local.room.GameDao
import com.example.core.data.local.room.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase =
        Room.databaseBuilder(context, GameDatabase::class.java, "Game.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideGameDao(database: GameDatabase): GameDao = database.gameDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(gameDao: GameDao): LocalDataSource = LocalDataSource(gameDao)
}