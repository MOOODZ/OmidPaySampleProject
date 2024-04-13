package ir.moodz.omidpaysampleproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import ir.moodz.omidpaysampleproject.domain.model.ProductListing

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(
        productListEntity: List<ProductEntity>
    )

    @Query("DELETE FROM ProductEntity")
    suspend fun clearAll()


    @Query(
        """
            SELECT *
            FROM ProductEntity
            WHERE LOWER(title) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchProducts(query: String): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE id LIKE :id")
    suspend fun productInfo(id:Int): ProductEntity

    @Query("UPDATE ProductEntity SET isBookmarked = :setBookmark WHERE id = :id")
    suspend fun setBookmark(id: Int, setBookmark:Int)

}