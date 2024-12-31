package org.dwivedi.marketoutlook.stock.presentation.stock_list

import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.Stock

sealed class StockListAction {

    data class OnBookmarkStock(val stock: Stock) : StockListAction()
    data class OnStockSelected(val stock: Stock) : StockListAction()
    data class OnSearchQueryChanged(val query: String) : StockListAction()
    data class OnExchangeSelected(val exchange: Exchange) : StockListAction()
}