package org.dwivedi.marketoutlook.stock.data.network

import org.dwivedi.marketoutlook.core.domain.DataError
import org.dwivedi.marketoutlook.core.domain.Result
import org.dwivedi.marketoutlook.stock.data.dto.ExchangeDto
import org.dwivedi.marketoutlook.stock.data.dto.ExchangeRootDto
import org.dwivedi.marketoutlook.stock.data.dto.StockDto
import org.dwivedi.marketoutlook.stock.data.dto.StockProfileDto
import org.dwivedi.marketoutlook.stock.data.dto.StockRootDto
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.Stock


interface StockApi {
     suspend fun getExchangeList(): Result<ExchangeRootDto, DataError.Remote>
     suspend fun getStockList(exchange: Exchange): Result<StockRootDto, DataError.Remote>
     suspend fun getStockProfile(symbol: String): Result<StockProfileDto, DataError.Remote>
}