package org.dwivedi.marketoutlook.stock.presentation.exchanges_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.dwivedi.marketoutlook.core.domain.onError
import org.dwivedi.marketoutlook.core.domain.onSuccess
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.StockRepository

class ExchangeListViewModel(
    private val repository: StockRepository
) : ViewModel() {

    private var cachedExchanges = emptyList<Exchange>()
    private var searchJob: Job? = null

    private val _state = MutableStateFlow(ExchangeListState())
    val state = _state
        .onStart {
            if (cachedExchanges.isEmpty()) {
                fetchExchangeList()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )


    private fun fetchExchangeList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                repository.getExchangeList().onSuccess { exchanges ->
                    cachedExchanges = exchanges
                    _state.update { it.copy(exchanges = exchanges, isLoading = false) }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            exchanges = emptyList(),
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

    fun onAction(action: ExchangeListAction) {
        when (action) {
            is ExchangeListAction.OnSearchQueryChanged -> filterExchanges(action.query)
            is ExchangeListAction.OnBookmarkExchange -> onBookmarkExchange(action.exchange)
            is ExchangeListAction.OnExchangeSelected -> onExchangeSelected(action.exchange)
        }
    }

    private fun filterExchanges(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val filteredList = if (query.isEmpty()) {
                cachedExchanges
            } else {
                cachedExchanges.filter { exchange ->
                    exchange.name.contains(query, ignoreCase = true) ||
                            exchange.code.contains(query, ignoreCase = true) ||
                            exchange.country.contains(query, ignoreCase = true)
                }
            }
            _state.update { it.copy(exchanges = filteredList) }
        }
    }

    private fun onBookmarkExchange(exchange: Exchange) {
        // Implement bookmark logic if needed
    }

    private fun onExchangeSelected(exchange: Exchange) {
        // Handle exchange selection
    }
}