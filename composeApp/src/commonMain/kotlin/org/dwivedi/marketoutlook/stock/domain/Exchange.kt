package org.dwivedi.marketoutlook.stock.domain

import kotlinx.serialization.Serializable

@Serializable
data class Exchange(
    val name: String = "",
    val code: String = "",
    val country: String = "",
    val timezone: String = ""
)
