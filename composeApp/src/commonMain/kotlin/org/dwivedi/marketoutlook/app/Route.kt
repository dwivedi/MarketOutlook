package org.dwivedi.marketoutlook.app

import kotlinx.serialization.Serializable
import org.dwivedi.marketoutlook.stock.domain.Stock

sealed interface Route {

    @Serializable
    data class StockDetails(val stock: Stock) : Route

    @Serializable
    data object StockList : Route

    @Serializable
    data object ExchangeList : Route

    @Serializable
    data object StockGraph : Route


}