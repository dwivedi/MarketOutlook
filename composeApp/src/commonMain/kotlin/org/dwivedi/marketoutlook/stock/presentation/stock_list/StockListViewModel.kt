package org.dwivedi.marketoutlook.stock.presentation.stock_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.dwivedi.marketoutlook.core.domain.onError
import org.dwivedi.marketoutlook.core.domain.onSuccess
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.Stock
import org.dwivedi.marketoutlook.stock.domain.StockRepository

class StockListViewModel(private val repository: StockRepository) : ViewModel() {

    private val _state = MutableStateFlow(StockListState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private fun fetchStockList(exchange: Exchange) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, exchange = exchange) }
            try {
                repository.getStockList(exchange).onSuccess { stocks ->
                    _state.update { it.copy(stocks = stocks, isLoading = false) }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            stocks = emptyList(),
                            isLoading = false,
                            error = error.toString()
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun onAction(action: StockListAction) {
        when (action) {
            is StockListAction.OnSearchQueryChanged -> updateFilteredStocks(query = action.query)
            is StockListAction.OnBookmarkStock -> onBookmarkStock(action.stock)
            is StockListAction.OnStockSelected -> onStockSelected(action.stock)
            is StockListAction.OnExchangeSelected -> {
                fetchStockList(action.exchange)
            }
        }
    }

    private fun onBookmarkStock(stock: Stock) {
    }

    private fun onStockSelected(stock: Stock) {
    }

    private fun updateFilteredStocks(query: String) {
        if (query.isBlank()) {
            _state.update { it.copy(filteredStocks = emptyList()) }
        } else {
            _state.update { it.copy(filteredStocks = _state.value.stocks.filter {
                it.name.startsWith(query, ignoreCase = true) ||
                        it.symbol.startsWith(query, ignoreCase = true)
            }) }
        }
    }
}
