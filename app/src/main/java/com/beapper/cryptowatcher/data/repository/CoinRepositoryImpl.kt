package com.beapper.cryptowatcher.data.repository

import android.util.Log
import com.beapper.cryptowatcher.common.Response
import com.beapper.cryptowatcher.data.remote.Api
import com.beapper.cryptowatcher.data.remote.dto.toCoin
import com.beapper.cryptowatcher.data.remote.dto.toCoinDetail
import com.beapper.cryptowatcher.domain.model.Coin
import com.beapper.cryptowatcher.domain.model.CoinDetail
import com.beapper.cryptowatcher.domain.repository.CoinRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: Api
) : CoinRepository {
    override suspend fun getCoins(): Response<List<Coin>> {
        return try {
            val coinsList = api.getCoins().map {
                it.toCoin()
            }
            Response.Success(coinsList)
        } catch (ex: HttpException) {
            Response.Error(ex.localizedMessage ?: "Unexpected error")
        } catch (ex: IOException) {
            Response.Error(ex.localizedMessage ?: "Unexpected error")
        }
    }

    override suspend fun getCoinById(coinId: String): Response<CoinDetail> {
        return try {
            val coin = api.getCoinById(coinId = coinId).toCoinDetail()
            Response.Success(coin)
        } catch (ex: HttpException) {
            Response.Error(ex.localizedMessage ?: "Unexpected error")
        } catch (ex: IOException) {
            Response.Error(ex.localizedMessage ?: "Unexpected error")
        }
    }
}