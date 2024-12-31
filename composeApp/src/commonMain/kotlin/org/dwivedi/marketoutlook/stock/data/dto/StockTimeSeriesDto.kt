package org.dwivedi.marketoutlook.stock.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockTimeSeriesDto(
    val meta: StockMeta,
    val values: List<StockPrice>,
    val status: String,
)

@Serializable
data class StockMeta(
    val symbol: String,
    val interval: String,
    val currency: String,
    @SerialName("exchange_timezone")
    val exchangeTimezone: String,
    val exchange: String,
    @SerialName("mic_code")
    val micCode: String,
    val type: String,
)

@Serializable
data class StockPrice(
    val datetime: String,
    val open: String,
    val high: String,
    val low: String,
    val close: String,
    val volume: String,
)
