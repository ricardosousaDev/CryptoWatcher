package com.beapper.cryptowatcher.presentation.coindetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beapper.cryptowatcher.common.Constants
import com.beapper.cryptowatcher.common.Response
import com.beapper.cryptowatcher.domain.usecase.GetCoinUseCase
import com.beapper.cryptowatcher.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CoinDetailUiModel())
    val state: State<CoinDetailUiModel> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoin(it)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { response ->
            when (response) {
                is Response.Success -> {
                    _state.value = CoinDetailUiModel(coin = response.data)
                }
                is Response.Error -> {
                    _state.value = CoinDetailUiModel(
                        error = response.message ?: "Unexpected error"
                    )
                }
                is Response.Loading -> {
                    _state.value = CoinDetailUiModel(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}