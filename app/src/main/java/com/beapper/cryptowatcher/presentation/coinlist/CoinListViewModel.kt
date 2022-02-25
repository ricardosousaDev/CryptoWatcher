package com.beapper.cryptowatcher.presentation.coinlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beapper.cryptowatcher.common.Response
import com.beapper.cryptowatcher.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CoinListUiModel())
    val state: State<CoinListUiModel> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { response ->
            when (response) {
                is Response.Success -> {
                    _state.value = CoinListUiModel(coins = response.data ?: emptyList())
                }
                is Response.Error -> {
                    _state.value = CoinListUiModel(
                        error = response.message ?: "Unexpected error"
                    )
                }
                is Response.Loading -> {
                    _state.value = CoinListUiModel(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}