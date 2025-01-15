package com.example.made.di

import com.example.core.domain.repository.IGameRepository
import com.example.core.domain.usecase.GameInteractor
import com.example.core.domain.usecase.GameUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideGameUseCase(gameInteractor: GameInteractor): GameUseCase

    companion object {
        @Provides
        @Singleton
        fun provideGameInteractor(gameRepository: IGameRepository): GameInteractor {
            return GameInteractor(gameRepository)
        }
    }
}