package org.dwivedi.marketoutlook.stock.presentation.stock_profile

import org.dwivedi.marketoutlook.stock.domain.LogoSymbol
import org.dwivedi.marketoutlook.stock.domain.Stock

sealed class StockProfileAction {

    data class OpenChart(val symbol: String): StockProfileAction()
    data class LoadStock(val stock: Stock) : StockProfileAction()

}
