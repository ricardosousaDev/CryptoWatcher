package com.beapper.cryptowatcher.domain.usecase

import com.beapper.cryptowatcher.common.Response
import com.beapper.cryptowatcher.domain.model.Coin
import com.beapper.cryptowatcher.domain.model.CoinDetail
import com.beapper.cryptowatcher.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Response<CoinDetail>> = flow {
        emit(Response.Loading())
        emit(coinRepository.getCoinById(coinId = coinId))
    }
}