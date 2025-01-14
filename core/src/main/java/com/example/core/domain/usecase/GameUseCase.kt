package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getDetailGame(id: Int): Flow<Resource<Game>>
    fun getFavoriteGames(): Flow<List<Game>>
    suspend fun setFavoriteGame(game: Game, state: Boolean)
}