package com.mmfsin.tnt.presentation.productdetail

import androidx.lifecycle.SavedStateHandle
import com.mmfsin.tnt.domain.usecases.DeleteProductUseCase
import com.mmfsin.tnt.domain.usecases.GetProductByIdUseCase
import com.mmfsin.tnt.domain.usecases.UpdateFavoriteProductUseCase
import com.mmfsin.tnt.domain.usecases.UpdateHaveProductUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val updateHaveProductUseCase: UpdateHaveProductUseCase,
    private val updateFavoriteProductUseCase: UpdateFavoriteProductUseCase,
) : BaseViewModel<ProductDetailStates>(ProductDetailStates()) {

    private val productId: String? = savedStateHandle["id"]

    init {
        getProductById()
    }

    fun sww() = _uiState.update { it.copy(sww = true) }

    fun getProductById() {
        productId?.let { id ->
            executeUseCase({ getProductByIdUseCase(id) }, { product ->
                if (product == null) sww()
                else _uiState.update {
                    it.copy(
                        product = product,
                        productId = product.id,
                        newName = product.name,
                        newWhereTo = product.whereToFind ?: "",
                        newInfo = product.info ?: "",
                        haveIt = product.haveIt,
                        isFavorite = product.favorite,
                    )
                }
            }, { sww() })
        } ?: run { sww() }
    }

    fun onNameChanged(value: String) = _uiState.update { it.copy(newName = value) }
    fun onWhereToChanged(value: String) = _uiState.update { it.copy(newWhereTo = value) }
    fun onInfoChanged(value: String) = _uiState.update { it.copy(newInfo = value) }

    fun updateDeleteDialogVisibility() = _uiState.update { it.copy(deleteDialog = !it.deleteDialog) }

    fun deleteProduct() {
        executeUseCase(
            { deleteProductUseCase(_uiState.value.productId) },
            {
                updateDeleteDialogVisibility()
                _uiState.update { it.copy(finishAndGoBack = true) }
            },
            { sww() }
        )
    }

    fun updateHaveIt(value:Boolean) {
        val state = _uiState.value
        executeUseCase(
            { updateHaveProductUseCase(state.productId, value) },
            { _uiState.update { it.copy(haveIt = !it.haveIt) } },
            {}
        )
    }

    fun updateFavorite(value:Boolean) {
        val state = _uiState.value
        executeUseCase(
            { updateFavoriteProductUseCase(state.productId, value) },
            { _uiState.update { it.copy(isFavorite = !it.isFavorite) } },
            {}
        )
    }
}