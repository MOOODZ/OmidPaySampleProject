package ir.moodz.omidpaysampleproject.presentation.products_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.moodz.omidpaysampleproject.presentation.products_list.components.ProductItem

@Composable
@Destination(start = true)
fun ProductListingsScreen (
    navigator: DestinationsNavigator,
    viewModel: ProductsListViewModel = hiltViewModel()
){
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {searchedText ->
                viewModel.onEvent(
                    ProductListingEvent.OnSearchQueryChange(searchedText)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search ...")
            },
            maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(ProductListingEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.productListings.size){ index ->
                    val product = state.productListings[index]
                    ProductItem(
                        product = product,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // Todo: Navigate to detail screen
                            }
                    )
                    // Make sure that the last item doesn't have a divider
                    if (index < state.productListings.size){
                        Divider(modifier = Modifier.padding(
                                horizontal = 16.dp
                        ))
                    }

                }
            }

        }

    }


}