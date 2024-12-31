package org.dwivedi.marketoutlook.di

import org.dwivedi.marketoutlook.core.data.HttpClientFactory
import org.dwivedi.marketoutlook.stock.data.network.StockApi
import org.dwivedi.marketoutlook.stock.data.network.StockApiImpl
import org.dwivedi.marketoutlook.stock.data.repository.StockRepositoryImpl
import org.dwivedi.marketoutlook.stock.domain.StockRepository
import org.dwivedi.marketoutlook.stock.presentation.exchanges_list.ExchangeListViewModel
import org.dwivedi.marketoutlook.stock.presentation.stock_list.StockListViewModel
import org.dwivedi.marketoutlook.stock.presentation.stock_profile.StockProfileViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

 val sharedModule = module {
     single { HttpClientFactory.create(get()) }
     singleOf(::StockApiImpl).bind<StockApi>()
     singleOf(::StockRepositoryImpl).bind<StockRepository>()
     viewModelOf(::ExchangeListViewModel)
     viewModelOf(::StockProfileViewModel)
     viewModelOf(::StockListViewModel)
  }
