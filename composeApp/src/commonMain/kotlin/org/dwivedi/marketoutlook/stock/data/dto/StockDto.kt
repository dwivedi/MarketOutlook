package org.dwivedi.marketoutlook.stock.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.dwivedi.marketoutlook.stock.domain.Stock

@Serializable
data class StockDto (
    val symbol: String,
    val name: String,
    val currency: String,
    val exchange: String,
    @SerialName("mic_code")
    val micCode: String,
    val country: String,
    val type: String,
    @SerialName("figi_code")
    val figiCode: String,
)

@Serializable
data class StockRootDto(
    @SerialName("data")
    val stocks: List<StockDto> = emptyList()
)