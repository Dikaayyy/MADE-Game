package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    var id: Int,
    val name: String,
    val rating: Float,
    val released: String,
    val backgroundImage: String,
    var description: String,
    var isFavorite: Boolean
) : Parcelable