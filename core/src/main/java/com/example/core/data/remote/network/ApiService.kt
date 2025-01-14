package com.example.core.data.remote.network

import com.example.core.data.remote.response.GameDetailResponse
import com.example.core.data.remote.response.GameListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("games")
    suspend fun getGames(): GameListResponse

    @GET("games/{id}")
    suspend fun getGameDetail(@Path("id") id: Int): GameDetailResponse
}