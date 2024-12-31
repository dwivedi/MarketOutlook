package org.dwivedi.marketoutlook.stock.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.dwivedi.marketoutlook.core.data.safeCall
import org.dwivedi.marketoutlook.core.domain.DataError
import org.dwivedi.marketoutlook.core.domain.Result
import org.dwivedi.marketoutlook.stock.data.dto.ExchangeDto
import org.dwivedi.marketoutlook.stock.data.dto.ExchangeRootDto
import org.dwivedi.marketoutlook.stock.data.dto.StockProfileDto
import org.dwivedi.marketoutlook.stock.data.dto.StockRootDto
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.Stock

const val BASE_URL_TWELVE_DATA = "https://api.twelvedata.com"
const val BASE_URL_ALPHA_VANTAGE = "https://www.alphavantage.co/query"
const val API_KEY_TWELVE_DATA = "f9a8d3a0e9fd4847a2e23792d9fc7ae7"
const val API_KEY_ALPHA_VANTAGE = "GQFHC0QMY0KKGVEK"


class StockApiImpl(
    private val httpClient: HttpClient
): StockApi {

    //https://api.twelvedata.com/quote?symbol=AADI&apikey=f9a8d3a0e9fd4847a2e23792d9fc7ae7
    override suspend fun getExchangeList(): Result<ExchangeRootDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL_TWELVE_DATA/exchanges") {
                parameter("apikey", API_KEY_TWELVE_DATA)
            }
        }
    }

    //https://api.twelvedata.com/stocks?exchange=XNCM
    override suspend fun getStockList(exchange: Exchange): Result<StockRootDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL_TWELVE_DATA/stocks") {
                parameter("exchange", exchange.code)
                parameter("apikey", API_KEY_TWELVE_DATA)
            }
        }
    }

    //https://www.alphavantage.co/query?function=OVERVIEW&=IsymbolBM&apikey=demo
    override suspend fun getStockProfile(symbol: String): Result<StockProfileDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL_ALPHA_VANTAGE/query") {
                parameter("function", "OVERVIEW")
                parameter("symbol", symbol)
                parameter("apikey", API_KEY_ALPHA_VANTAGE)
            }
        }
    }


}