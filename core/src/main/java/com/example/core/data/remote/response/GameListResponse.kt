package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GameListResponse(
    @SerializedName("results") val results: List<GameResponse>
)