package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getDetailGame(id: Int): Flow<Resource<Game>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)
}