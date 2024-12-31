package org.dwivedi.marketoutlook.stock.presentation.exchanges_list

import org.dwivedi.marketoutlook.stock.domain.Exchange

sealed class ExchangeListAction {
    data class OnBookmarkExchange(val exchange: Exchange) : ExchangeListAction()
    data class OnExchangeSelected(val exchange: Exchange) : ExchangeListAction()
    data class OnSearchQueryChanged(val query: String) : ExchangeListAction()
}