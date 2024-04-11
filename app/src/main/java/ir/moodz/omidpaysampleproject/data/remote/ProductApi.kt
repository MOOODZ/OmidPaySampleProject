package ir.moodz.omidpaysampleproject.data.remote

import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    companion object{
        const val BASE_URL = "https://fakestoreapi.com/"
    }

}