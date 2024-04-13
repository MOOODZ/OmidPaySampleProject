package ir.moodz.omidpaysampleproject.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProductListing(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val isBookmarked: Int
)
