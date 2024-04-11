package ir.moodz.omidpaysampleproject.presentation.products_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.moodz.omidpaysampleproject.domain.repository.ProductRepository
import ir.moodz.omidpaysampleproject.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    var state by mutableStateOf(ProductsListingState())
    private var searchJob: Job? = null
    fun onEvent(event: ProductListingEvent) {

        when (event) {

            is ProductListingEvent.Refresh -> {
                getProductListing(fetchFromApi = true)
            }
            is ProductListingEvent.OnSearchQueryChange ->{
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getProductListing()
                }
            }

        }


    }

    private fun getProductListing(
        query: String = state.searchQuery.lowercase(),
        fetchFromApi: Boolean = false
    ){
        viewModelScope.launch {
            repository
                .getProductListing(fetchFromApi, query)
                .collect { result ->
                    when(result){
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(productListings = listings)
                            }
                        }
                        is Resource.Error -> {
                            // Todo: Handle errors
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }

        }

    }

}