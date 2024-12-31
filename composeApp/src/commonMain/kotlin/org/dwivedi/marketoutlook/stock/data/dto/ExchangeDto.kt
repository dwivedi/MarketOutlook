package org.dwivedi.marketoutlook.stock.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeDto(
    var name : String,
    var code: String,
    var country  : String,
    var timezone : String
)

@Serializable
data class ExchangeRootDto(
    @SerialName("data")
    val exchanges: List<ExchangeDto> = emptyList()
)