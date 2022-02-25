package com.beapper.cryptowatcher.domain.repository

import com.beapper.cryptowatcher.common.Response
import com.beapper.cryptowatcher.domain.model.Coin
import com.beapper.cryptowatcher.domain.model.CoinDetail

interface CoinRepository {

    suspend fun getCoins(): Response<List<Coin>>

    suspend fun getCoinById(coinId: String): Response<CoinDetail>
}