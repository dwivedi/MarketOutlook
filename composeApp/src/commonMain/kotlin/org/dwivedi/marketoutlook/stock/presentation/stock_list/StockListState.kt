package org.dwivedi.marketoutlook.stock.presentation.stock_list

import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.Stock

data class StockListState(
    val isLoading: Boolean = false,
    val stocks: List<Stock> = emptyList(),
    val filteredStocks: List<Stock> = emptyList(),
    val exchange: Exchange = Exchange(
        name = "NASDAQ",
        code = "XNCM",
        country = "United States",
        timezone = "America/New_York"
    ),
    val error: String? = null,
    val searchQuery: String = ""
)