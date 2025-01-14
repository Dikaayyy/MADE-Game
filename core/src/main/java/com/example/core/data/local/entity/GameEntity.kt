package com.example.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "released") val released: String,
    @ColumnInfo(name = "background_image") val backgroundImage: String,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean
)