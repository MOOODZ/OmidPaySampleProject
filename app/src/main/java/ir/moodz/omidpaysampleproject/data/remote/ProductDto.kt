package ir.moodz.omidpaysampleproject.data.remote


data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val isBookmarked: Int
)

