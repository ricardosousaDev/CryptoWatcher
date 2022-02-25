package com.beapper.cryptowatcher.presentation.coinlist

import com.beapper.cryptowatcher.domain.model.Coin

data class CoinListUiModel(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
