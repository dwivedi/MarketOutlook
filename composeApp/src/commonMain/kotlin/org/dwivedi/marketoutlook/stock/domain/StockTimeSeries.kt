package org.dwivedi.marketoutlook.stock.domain

data class StockTimeSeries(
    val meta: StockMeta,
    val values: List<StockPrice>,
    val status: String,
)

data class StockMeta(
    val symbol: String,
    val interval: String,
    val currency: String,
    val exchangeTimezone: String,
    val exchange: String,
    val micCode: String,
    val type: String,
)

data class StockPrice(
    val datetime: String,
    val open: String,
    val high: String,
    val low: String,
    val close: String,
    val volume: String,
)
