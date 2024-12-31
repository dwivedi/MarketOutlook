package org.dwivedi.marketoutlook.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.presentation.exchanges_list.ExchangeListScreenRoot
import org.dwivedi.marketoutlook.stock.presentation.exchanges_list.ExchangeListViewModel
import org.dwivedi.marketoutlook.stock.presentation.stock_list.StockListViewModel
import org.dwivedi.marketoutlook.stock.presentation.stock_list.StockScreenRoot
import org.dwivedi.marketoutlook.stock.presentation.stock_profile.StockProfileScreenRoot
import org.dwivedi.marketoutlook.stock.presentation.stock_profile.StockProfileViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    MaterialTheme {

        val navController = rememberNavController()
        val appViewModel = remember { AppViewModel() }

        AppNavHost(
            navController = navController,
            appViewModel = appViewModel,
        )
    }
}


@Composable
fun AppNavHost(
    navController: NavHostController,
    appViewModel: AppViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = "ExchangeList"
    ) {
        composable("ExchangeList") {
            ExchangeListScreenRoot(
                onExchangeSelected = { exchange ->
                    appViewModel.setSelectedExchange(exchange)
                    navController.navigate("StockList")
                }
            )
        }
        composable("StockList") {
            val selectedExchange = appViewModel.selectedExchange.collectAsState().value
            StockScreenRoot(
                exchange = selectedExchange,
                onStockSelected = { stock ->
                    appViewModel.setSelectedStock(stock)
                    navController.navigate("StockDetails")
                }
            )
        }
        composable("StockDetails") {
            val stock = appViewModel.selectedStock.collectAsState().value

            if (stock != null) {
                StockProfileScreenRoot(
                    stock = stock,
                )
            }
        }
    }
}
