package ir.moodz.omidpaysampleproject.presentation.product_listing

sealed class ProductListingEvent {
    data object Refresh: ProductListingEvent()
    data class OnSearchQueryChange(val query: String): ProductListingEvent()
}