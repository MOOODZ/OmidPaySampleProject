package ir.moodz.omidpaysampleproject.presentation.products_list

sealed class ProductListingEvent {
    data object Refresh: ProductListingEvent()
    data class OnSearchQueryChange(val query: String): ProductListingEvent()
}