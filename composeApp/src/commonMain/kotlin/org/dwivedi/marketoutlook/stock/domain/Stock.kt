package org.dwivedi.marketoutlook.stock.domain

import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val symbol: String,
    val name: String,
    val exchange: String,
    val currency: String,
    val micCode: String,
    val figiCode: String,
    val country: String
)