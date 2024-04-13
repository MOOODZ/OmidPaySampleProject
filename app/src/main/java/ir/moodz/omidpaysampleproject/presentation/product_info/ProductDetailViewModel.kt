package ir.moodz.omidpaysampleproject.presentation.product_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.moodz.omidpaysampleproject.domain.repository.ProductRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val saveStateHandle:SavedStateHandle,
    private val repository: ProductRepository
): ViewModel() {

    var state by mutableStateOf(ProductDetailState())

    init {
        viewModelScope.launch {
            val productId = saveStateHandle.get<Int>("id") ?: return@launch
            state = state.copy(isLoading = true)
            val productDetail = repository.getProductDetail(productId)

            productDetail.collect{ product ->
                state = state.copy(
                    productDetail = product,
                    isLoading = false,
                    isBookmarked = product.isBookmarked
                )
            }
        }
    }

    fun onEvent(event:ProductEvents){
        when(event){
            is ProductEvents.BookmarkProduct ->{
                viewModelScope.launch{
                    when(state.isBookmarked){
                        0 -> {
                            state = state.copy(isBookmarked = 1)
                            repository.setBookmark(event.productId , 1)
                        }
                        1 -> {
                            state = state.copy(isBookmarked = 0)
                            repository.setBookmark(event.productId , 0)
                        }
                    }
                }
            }
        }
    }




}