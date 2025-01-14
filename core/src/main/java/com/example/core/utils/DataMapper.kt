package com.example.core.utils

import com.example.core.data.local.entity.GameEntity
import com.example.core.data.remote.response.GameDetailResponse
import com.example.core.data.remote.response.GameResponse
import com.example.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> =
        input.map {
            GameEntity(
                id = it.id,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                description = "",
                isFavorite = false
            )
        }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                description = it.description,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game): GameEntity =
        GameEntity(
            id = input.id,
            name = input.name,
            released = input.released,
            backgroundImage = input.backgroundImage,
            rating = input.rating,
            description = input.description,
            isFavorite = input.isFavorite
        )

    fun mapEntityToDomain(input: GameEntity): Game =
        Game(
            id = input.id,
            name = input.name,
            released = input.released,
            backgroundImage = input.backgroundImage,
            rating = input.rating,
            description = input.description,
            isFavorite = input.isFavorite
        )

    fun mapResponseToEntity(input: GameDetailResponse): GameEntity =
        GameEntity(
            id = input.id,
            name = input.name,
            released = input.released,
            backgroundImage = input.backgroundImage,
            rating = input.rating,
            description = input.description,
            isFavorite = false
        )
}