package org.dwivedi.marketoutlook.stock.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class StockLogoDto(
    val meta: LogoSymbolDto? = null,
    val url: String? = null,
)


@Serializable
data class LogoSymbolDto(
    val symbol: String,
)
