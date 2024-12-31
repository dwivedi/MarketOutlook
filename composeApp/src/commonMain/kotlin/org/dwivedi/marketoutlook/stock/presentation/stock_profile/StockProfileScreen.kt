package org.dwivedi.marketoutlook.stock.presentation.stock_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.LogoSymbol
import org.dwivedi.marketoutlook.stock.domain.Stock
import org.dwivedi.marketoutlook.stock.domain.StockProfile
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun StockProfileScreenRoot(stock: Stock,
                           viewModel: StockProfileViewModel = koinViewModel(),
                           ) {

    LaunchedEffect(stock) {
        viewModel.onAction(StockProfileAction.LoadStock(stock))
    }


    val state by viewModel.state.collectAsStateWithLifecycle()

    StockProfileScreen(state = state){
        viewModel.onAction(it)
    }
}

@Composable
fun StockProfileScreen(state: StockProfileState, onAction: (StockProfileAction) -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            state.stockProfile?.let { stock->
                StockDetailsScreenModern(stock)
            }
        }
    }
}

@Composable
fun StockDetailsScreenModern(stock: StockProfile) {
    with(stock){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StockNameSection(stock)

            KeyPointsSection(stock)

            DetailsCard(stock)
        }
    }

}
@Composable
fun StockNameSection(stock: StockProfile) {
    // Stock Name
    Text(
        text = stock.name,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Description
    Text(
        text = stock.description,
        style = MaterialTheme.typography.body2,
        lineHeight = 20.sp
    )

}

@Composable
fun KeyPointsSection(stock: StockProfile) {
    Text(
        text = "KEY POINTS",
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(8.dp))

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
        ) {
            StockDetailRow("Market Cap", stock.marketCapitalization)
            StockDetailRow("P/E Ratio", stock.peratio)
            StockDetailRow("EPS", stock.eps)
            StockDetailRow("Revenue (TTM)", stock.revenueTtm)
            StockDetailRow("Gross Profit (TTM)", stock.grossProfitTtm)
            StockDetailRow("Dividend Per Share", stock.dividendPerShare)
            StockDetailRow("Dividend Yield", stock.dividendYield)
        }
    }
}

@Composable
fun DetailsCard(stock: StockProfile) {

    Text(
        text = "OTHER DETAILS",
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.SemiBold
    )

    Spacer(modifier = Modifier.height(8.dp))

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StockDetailRow("Address", stock.address)
            StockDetailRow("Exchange", stock.exchange)
            StockDetailRow("Sector", stock.sector)
            StockDetailRow("Industry", stock.industry)
            StockDetailRow("Official Site", stock.officialSite)
        }
    }
}

@Composable
fun StockDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )
    }
}








