package org.dwivedi.marketoutlook.stock.presentation.exchanges_list

import org.dwivedi.marketoutlook.stock.domain.Exchange

data class ExchangeListState(
    val isLoading: Boolean = false,
    val exchanges: List<Exchange> = emptyList(),
    val error: String? = null
)