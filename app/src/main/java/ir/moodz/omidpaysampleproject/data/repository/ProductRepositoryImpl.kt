package ir.moodz.omidpaysampleproject.data.repository

import ir.moodz.omidpaysampleproject.data.local.ProductDatabase
import ir.moodz.omidpaysampleproject.data.mappers.toProduct
import ir.moodz.omidpaysampleproject.data.mappers.toProductEntity
import ir.moodz.omidpaysampleproject.data.remote.ProductApi
import ir.moodz.omidpaysampleproject.domain.model.ProductListing
import ir.moodz.omidpaysampleproject.domain.repository.ProductRepository
import ir.moodz.omidpaysampleproject.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    val api: ProductApi,
    val db: ProductDatabase
) : ProductRepository {

    private val dao = db.productDao
    override suspend fun getProductListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<ProductListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val productsList = dao.searchProducts(query)
            emit(Resource.Success(
                data = productsList.map { it.toProduct() }
            ))

            val isDbEmpty = productsList.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteProduct = try {
                val response = api.getProducts()
                response.sortedBy { it.price }
            } catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            // We first clear and only then insert the products because of the Single Source of Truth principle
            remoteProduct?.let {products ->
                dao.clearAll()
                dao.insertProducts(
                    products.map { it.toProductEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchProducts("")
                        .map { it.toProduct() }
                ))
                emit(Resource.Loading(false))

            }

        }
    }
}