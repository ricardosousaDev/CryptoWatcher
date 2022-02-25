package com.beapper.cryptowatcher.presentation.coindetail

import com.beapper.cryptowatcher.domain.model.CoinDetail

data class CoinDetailUiModel(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
