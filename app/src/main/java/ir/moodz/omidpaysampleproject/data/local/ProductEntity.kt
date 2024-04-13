package ir.moodz.omidpaysampleproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val isBookmarked: Int
)
