package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("released")
    val released: String,

    @SerializedName("background_image")
    val backgroundImage: String,

    @SerializedName("rating")
    val rating: Float
)