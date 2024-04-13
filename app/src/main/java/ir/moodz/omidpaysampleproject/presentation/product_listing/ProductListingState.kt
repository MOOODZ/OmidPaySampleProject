package ir.moodz.omidpaysampleproject.presentation.product_listing

import ir.moodz.omidpaysampleproject.domain.model.ProductListing

data class ProductListingState (
    val productListings : List<ProductListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)