package org.dwivedi.marketoutlook.stock.presentation.exchanges_list

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ExchangeListScreenRoot(
    viewModel: ExchangeListViewModel = koinViewModel(),
    onExchangeSelected: (Exchange) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ExchangeListScreen(state = state, onAction = { action ->
        when (action) {
            is ExchangeListAction.OnExchangeSelected -> onExchangeSelected(action.exchange)
            else -> Unit
        }
        viewModel.onAction(action)
    })
}

@Composable
private fun ExchangeListScreen(
    state: ExchangeListState,
    onAction: (ExchangeListAction) -> Unit
) {
    var isSearchBarVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isSearchBarVisible = !isSearchBarVisible }
            ) {
                Icon(
                    imageVector = if (isSearchBarVisible) Icons.Default.Close else Icons.Default.Search,
                    contentDescription = if (isSearchBarVisible) "Close Search" else "Open Search"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Title
            Text(
                text = "Exchange List",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Animated Search Bar with custom styling
            AnimatedVisibility(
                visible = isSearchBarVisible,
                enter = slideInVertically(initialOffsetY = { -it }, animationSpec = tween(300)) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(300)) + fadeOut()
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        onAction(ExchangeListAction.OnSearchQueryChanged(it))
                    },
                    label = { Text("Search exchanges...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.body1,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.primary,
                        unfocusedBorderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                        cursorColor = MaterialTheme.colors.primary
                    )
                )
            }

            // List or Loading Indicator
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    ExchangeList(state.exchanges) { // Dynamically updated exchanges list
                        onAction.invoke(ExchangeListAction.OnExchangeSelected(it))
                    }
                }
            }
        }
    }
}


// List composable for displaying exchanges
@Composable
fun ExchangeList(
    exchanges: List<Exchange>,
    onExchangeSelected: (Exchange) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(exchanges) { exchange ->
            ExchangeItem(exchange) {
                onExchangeSelected.invoke(exchange)
            }
        }
    }
}

@Composable
fun ExchangeItem(exchange: Exchange, onExchangeSelected: (Exchange) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onExchangeSelected(exchange) }
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Exchange Name and Code in first row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = exchange.name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary
                )
                Text(
                    text = exchange.code,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.secondary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Country and Timezone in second row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = exchange.country,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
                Text(
                    text = "Timezone: ${exchange.timezone}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}
