package ir.moodz.omidpaysampleproject.presentation.product_info

sealed class ProductEvents{
    data class BookmarkProduct(val productId:Int): ProductEvents()
}