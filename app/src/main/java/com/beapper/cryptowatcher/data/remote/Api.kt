package com.beapper.cryptowatcher.data.remote

import com.beapper.cryptowatcher.data.remote.dto.CoinDetailDto
import com.beapper.cryptowatcher.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}