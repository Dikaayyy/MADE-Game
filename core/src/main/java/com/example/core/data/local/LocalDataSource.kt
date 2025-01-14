package com.example.core.data.local

import com.example.core.data.local.entity.GameEntity
import com.example.core.data.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {

    fun getGames(): Flow<List<GameEntity>> = gameDao.getGames()

    fun getGameById(id: Int): Flow<GameEntity> = gameDao.getGameById(id)

    fun getFavoriteGames(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    suspend fun insertGames(games: List<GameEntity>) = gameDao.insertGames(games)

    suspend fun updateGame(game: GameEntity) = gameDao.updateGame(game)
}