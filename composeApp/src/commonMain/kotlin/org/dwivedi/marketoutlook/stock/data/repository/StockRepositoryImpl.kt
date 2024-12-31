package org.dwivedi.marketoutlook.stock.data.repository

import org.dwivedi.marketoutlook.core.domain.DataError
import org.dwivedi.marketoutlook.core.domain.Result
import org.dwivedi.marketoutlook.core.domain.map
import org.dwivedi.marketoutlook.stock.data.network.StockApi
import org.dwivedi.marketoutlook.stock.data.toExchange
import org.dwivedi.marketoutlook.stock.data.toStock
import org.dwivedi.marketoutlook.stock.data.toStockProfile
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.Stock
import org.dwivedi.marketoutlook.stock.domain.StockLogo
import org.dwivedi.marketoutlook.stock.domain.StockProfile
import org.dwivedi.marketoutlook.stock.domain.StockRepository
import org.dwivedi.marketoutlook.stock.domain.StockTimeSeries

class StockRepositoryImpl(
    private val api: StockApi
): StockRepository {

    override suspend fun getExchangeList(): Result<List<Exchange>, DataError> {
        return api.getExchangeList().map {
            println("the exchanges "+it.exchanges.size)
            it.exchanges.map { exchangeDto ->
                exchangeDto.toExchange()
            }
        }
    }

    override suspend fun getStockList(exchange: Exchange): Result<List<Stock>, DataError> {
        return api.getStockList(exchange).map {
            it.stocks.filter {stockDto ->
                stockDto.type == "Common Stock"
            }.map { stockDto ->
                stockDto.toStock()
            }
        }
    }

    override suspend fun getStockTimeSeries(stock: Stock): Result<StockTimeSeries, DataError> {
        TODO("Not yet implemented")
    }

    override suspend fun getStockLogo(stock: Stock): Result<StockLogo, DataError> {
        TODO("Not yet implemented")
    }

    override suspend fun getQuote(symbol: String): Result<StockProfile, DataError> {
        return api.getStockProfile(symbol).map {
            it.toStockProfile()
        }
    }

}