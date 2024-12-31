package org.dwivedi.marketoutlook.stock.domain

import org.dwivedi.marketoutlook.core.domain.DataError
import org.dwivedi.marketoutlook.core.domain.Result

interface StockRepository {
    suspend fun getExchangeList(): Result<List<Exchange>, DataError>
    suspend fun getStockList(exchange: Exchange):  Result<List<Stock>, DataError>
    suspend fun getStockTimeSeries(stock: Stock): Result<StockTimeSeries, DataError>
    suspend fun getStockLogo(stock: Stock): Result<StockLogo, DataError>
    suspend fun getQuote(symbol: String): Result<StockProfile, DataError>
}