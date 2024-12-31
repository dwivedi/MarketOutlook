package org.dwivedi.marketoutlook.stock.presentation.stock_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.Stock
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StockScreenRoot(
    exchange: Exchange? = null,
    viewModel: StockListViewModel = koinViewModel(),
    onStockSelected: (Stock) -> Unit,
) {

    exchange?.let {
        LaunchedEffect(exchange){
            viewModel.onAction(StockListAction.OnExchangeSelected(exchange))
        }
        val state by viewModel.state.collectAsStateWithLifecycle()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading){
                CircularProgressIndicator()
            }else {
                StockScreen(state = state, onAction = { action ->
                    when (action) {
                        is StockListAction.OnStockSelected -> onStockSelected(action.stock)
                        else -> Unit }
                    viewModel.onAction(action)
                })
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StockScreen(state: StockListState, onAction: (StockListAction) -> Unit) {
    val country = state.exchange.country
    val randomStocks = remember { state.stocks.shuffled().take(8) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Image at the center
        Image(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Stock analysis and screening tool for investors in $country.")
        Spacer(modifier = Modifier.height(16.dp))

        // Search Field with Suggestions
        var query by remember { mutableStateOf(TextFieldValue("")) }

        // Filtered suggestions based on input
        val filteredStocks = remember(query.text) {
            state.stocks.filter {
                it.name.startsWith(query.text, ignoreCase = true) ||
                        it.symbol.startsWith(query.text, ignoreCase = true)
            }
        }

        // TextField
        TextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text("Search for a company") },
            modifier = Modifier.fillMaxWidth(0.8f),
            singleLine = true,
            leadingIcon = {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (query.text.isBlank()) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    text = "Or analyse:",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                randomStocks.forEach { suggestion ->
                    SuggestionChip(
                        text = suggestion.symbol,
                        onClick = { onAction(StockListAction.OnStockSelected(suggestion)) }
                    )
                }
            }
        } else {
            // Show filtered list when query is not blank
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(200.dp) // Adjust height as needed
            ) {
                items(filteredStocks) { stock ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onAction(StockListAction.OnStockSelected(stock)) }
                            .padding(vertical = 8.dp, horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stock.name,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Divider()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SuggestionChip(
    text: String,
    onClick: () -> Unit
) {
    Chip(
        onClick = onClick,
        colors = ChipDefaults.chipColors(
            backgroundColor = Color.LightGray,
            contentColor = MaterialTheme.colors.onSurface
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            fontSize = 14.sp
        )
    }
}
