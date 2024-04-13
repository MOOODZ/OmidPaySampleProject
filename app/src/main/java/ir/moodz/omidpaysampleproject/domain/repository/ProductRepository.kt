package ir.moodz.omidpaysampleproject.domain.repository

import ir.moodz.omidpaysampleproject.domain.model.ProductListing
import ir.moodz.omidpaysampleproject.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<ProductListing>>>

    suspend fun getProductDetail(id: Int): Flow<ProductListing>

    suspend fun setBookmark(id:Int, setBookmark:Int)
}