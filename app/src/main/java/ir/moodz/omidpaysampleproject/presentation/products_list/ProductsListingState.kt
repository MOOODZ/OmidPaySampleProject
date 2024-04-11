package ir.moodz.omidpaysampleproject.presentation.products_list

import ir.moodz.omidpaysampleproject.domain.model.ProductListing

data class ProductsListingState (
    val productListings : List<ProductListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)