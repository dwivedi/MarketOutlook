package org.dwivedi.marketoutlook.stock.presentation.stock_profile

import org.dwivedi.marketoutlook.stock.domain.Stock
import org.dwivedi.marketoutlook.stock.domain.StockProfile

data class StockProfileState(val isLoading: Boolean = false,
                             val stockProfile: StockProfile? = null,
                             val stock: Stock? = null
)