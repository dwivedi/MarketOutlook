package org.dwivedi.marketoutlook.stock.domain

data class StockLogo(
    val meta: LogoSymbol,
    val url: String,
)

data class LogoSymbol(
    val symbol: String,
)
