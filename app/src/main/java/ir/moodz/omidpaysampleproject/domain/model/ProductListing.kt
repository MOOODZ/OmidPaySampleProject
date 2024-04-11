package ir.moodz.omidpaysampleproject.domain.model

data class ProductListing(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String
)
