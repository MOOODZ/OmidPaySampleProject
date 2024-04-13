package ir.moodz.omidpaysampleproject.presentation.product_info

import ir.moodz.omidpaysampleproject.domain.model.ProductListing

data class ProductDetailState (
    val productDetail: ProductListing? = null,
    val isLoading: Boolean = false,
    val isBookmarked: Int = 0
)