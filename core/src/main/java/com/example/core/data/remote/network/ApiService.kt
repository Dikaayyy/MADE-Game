package com.example.core.data.remote.network

import com.example.core.BuildConfig
import com.example.core.data.remote.response.GameDetailResponse
import com.example.core.data.remote.response.GameListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String=BuildConfig.RAWG_API_KEY,
    ): GameListResponse

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int,
        @Header("key") apiKey: String = BuildConfig.RAWG_API_KEY
    ): GameDetailResponse
}