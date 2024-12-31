package org.dwivedi.marketoutlook.stock.presentation.stock_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.dwivedi.marketoutlook.core.domain.onError
import org.dwivedi.marketoutlook.core.domain.onSuccess
import org.dwivedi.marketoutlook.stock.domain.Stock
import org.dwivedi.marketoutlook.stock.domain.StockRepository

class StockProfileViewModel(
    private val repository: StockRepository,
): ViewModel() {

    private val _state = MutableStateFlow(StockProfileState(isLoading = true))
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)


    private fun fetchStockProfile(stock: Stock) {
        viewModelScope.launch {
            repository.getQuote(stock.symbol).onSuccess {
                _state.value = _state.value.copy(
                    stockProfile = it,
                    isLoading = false,
                    stock = stock
                )
            }.onError {

            }
        }
    }

    fun onAction(action: StockProfileAction) {
        when(action){
            is StockProfileAction.LoadStock -> {
                fetchStockProfile(action.stock)
                _state.value = _state.value.copy(
                    stock = action.stock,
                )
            }
            else -> Unit
        }
    }
}