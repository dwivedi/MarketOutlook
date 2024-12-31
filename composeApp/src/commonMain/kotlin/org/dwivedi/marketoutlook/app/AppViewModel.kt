package org.dwivedi.marketoutlook.app

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.Stock

class AppViewModel : ViewModel() {
    private val _selectedExchange = MutableStateFlow<Exchange?>(null)
    val selectedExchange: StateFlow<Exchange?> = _selectedExchange

    private val _selectedStock = MutableStateFlow<Stock?>(null)
    val selectedStock: StateFlow<Stock?> = _selectedStock

    fun setSelectedExchange(exchange: Exchange) {
        _selectedExchange.value = exchange
    }

    fun setSelectedStock(stock: Stock) {
        _selectedStock.value = stock
    }
}
