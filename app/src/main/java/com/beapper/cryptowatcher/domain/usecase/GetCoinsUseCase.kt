package com.beapper.cryptowatcher.domain.usecase

import com.beapper.cryptowatcher.common.Response
import com.beapper.cryptowatcher.domain.model.Coin
import com.beapper.cryptowatcher.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(): Flow<Response<List<Coin>>> = flow {
        emit(Response.Loading())
        emit(coinRepository.getCoins())
    }
}