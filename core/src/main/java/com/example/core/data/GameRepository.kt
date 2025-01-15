package com.example.core.data

import com.example.core.data.local.LocalDataSource
import com.example.core.data.local.entity.GameEntity
import com.example.core.data.remote.RemoteDataSource
import com.example.core.data.remote.network.ApiResponse
import com.example.core.data.remote.response.GameResponse
import com.example.core.domain.model.Game
import com.example.core.domain.repository.IGameRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getAllGames(): Flow<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(gameList)
            }
        }.asFlow()
    }

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        gameEntity.isFavorite = state
        appExecutors.diskIO().execute {
            runBlocking {
                localDataSource.updateGame(gameEntity)
            }
        }
    }

    override fun getDetailGame(id: Int): Flow<Resource<Game>> = flow {
        emit(Resource.Loading())
        localDataSource.getGameById(id).collect { dbGame ->
            when (dbGame) {
                null -> {
                    // Game not found in local DB, fetch from remote
                    remoteDataSource.getDetailGame(id).collect { response ->
                        when (response) {
                            is ApiResponse.Success -> {
                                val responseGame = response.data
                                // Create new GameEntity
                                val gameEntity = GameEntity(
                                    id = id,
                                    name = responseGame.name,
                                    rating = responseGame.rating,
                                    released = responseGame.released,
                                    backgroundImage = responseGame.backgroundImage,
                                    description = responseGame.description ?: "",
                                    isFavorite = false
                                )
                                // Save to local DB
                                localDataSource.insertGames(listOf(gameEntity))

                                val gameDomain = DataMapper.mapEntityToDomain(gameEntity)
                                emit(Resource.Success(gameDomain))
                            }
                            is ApiResponse.Error -> {
                                emit(Resource.Error(response.errorMessage))
                            }
                            else -> {}
                        }
                    }
                }
                else -> {
                    // Game found in local DB
                    val gameDomain = Game(
                        id = dbGame.id,
                        name = dbGame.name,
                        rating = dbGame.rating,
                        released = dbGame.released,
                        backgroundImage = dbGame.backgroundImage,
                        description = dbGame.description,
                        isFavorite = dbGame.isFavorite
                    )

                    if (dbGame.description.isEmpty()) {
                        remoteDataSource.getDetailGame(id).collect { response ->
                            when (response) {
                                is ApiResponse.Success -> {
                                    val responseGame = response.data
                                    dbGame.description = responseGame.description
                                    localDataSource.updateGame(dbGame)

                                    gameDomain.description = responseGame.description
                                    emit(Resource.Success(gameDomain))
                                }
                                is ApiResponse.Error -> {
                                    emit(Resource.Error(response.errorMessage))
                                }
                                else -> {}
                            }
                        }
                    } else {
                        emit(Resource.Success(gameDomain))
                    }
                }
            }
        }
    }
}