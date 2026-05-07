package com.mmfsin.tnt.presentation.productdetail

import androidx.lifecycle.SavedStateHandle
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getCategoryById
import com.mmfsin.tnt.domain.usecases.DeleteProductUseCase
import com.mmfsin.tnt.domain.usecases.GetCategoriesUseCase
import com.mmfsin.tnt.domain.usecases.GetProductByIdUseCase
import com.mmfsin.tnt.domain.usecases.UpdateCategoryUseCase
import com.mmfsin.tnt.domain.usecases.UpdateFavoriteProductUseCase
import com.mmfsin.tnt.domain.usecases.UpdateHaveProductUseCase
import com.mmfsin.tnt.domain.usecases.UpdateProductUseCase
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
    private val updateProductUseCase: UpdateProductUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase
) : BaseViewModel<ProductDetailStates>(ProductDetailStates()) {

    private val productId: String? = savedStateHandle["id"]

    init {
        getProductById()
        getCategories()
    }

    fun sww() = _uiState.update { it.copy(sww = true) }

    fun getProductById() {
        productId?.let { id ->
            executeUseCase({ getProductByIdUseCase(id) }, { product ->
                if (product == null) sww()
                else _uiState.update {
                    it.copy(
                        oldProduct = product,
                        productId = product.id,
                        newName = product.name,
                        newWhereToFind = product.whereToFind ?: "",
                        newInfo = product.info ?: "",
                        newHaveIt = product.haveIt,
                        newFavorite = product.favorite,
                        newCategory = product.category
                    )
                }
            }, { sww() })
        } ?: run { sww() }
    }

    private fun getCategories() {
        executeUseCase(
            { getCategoriesUseCase() },
            { categories -> _uiState.update { it.copy(categories = categories) } },
            {}
        )
    }

    fun onNameChanged(value: String) = _uiState.update { it.copy(newName = value) }
    fun onWhereToFindChanged(value: String) = _uiState.update { it.copy(newWhereToFind = value) }
    fun onInfoChanged(value: String) = _uiState.update { it.copy(newInfo = value) }

    fun updateDeleteDialogVisibility() = _uiState.update { it.copy(deleteDialog = !it.deleteDialog) }
    fun updateCategoriesState() = _uiState.update { it.copy(categoriesState = !it.categoriesState) }

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

    fun updateHaveIt(value: Boolean) {
        val state = _uiState.value
        executeUseCase(
            { updateHaveProductUseCase(state.productId, value) },
            { _uiState.update { it.copy(newHaveIt = !it.newHaveIt) } },
            {}
        )
    }

    fun updateFavorite(value: Boolean) {
        val state = _uiState.value
        executeUseCase(
            { updateFavoriteProductUseCase(state.productId, value) },
            { _uiState.update { it.copy(newFavorite = !it.newFavorite) } },
            {}
        )
    }

    fun updateCategory(value: Int) {
        val state = _uiState.value
        executeUseCase(
            { updateCategoryUseCase(state.productId, value) },
            {
                _uiState.update { it.copy(newCategory = getCategoryById(value)) }
                updateCategoriesState()
            },
            {}
        )
    }

    fun saveAndUpdateProduct() {
        val originalProduct = _uiState.value.oldProduct ?: return sww()
        val states = _uiState.value

        val updatedProduct = originalProduct.copy(
            name = states.newName,
            info = states.newInfo.ifEmpty { null },
            whereToFind = states.newWhereToFind.ifEmpty { null },
            haveIt = states.newHaveIt,
            favorite = states.newFavorite,
            category = states.newCategory
        )

        executeUseCase(
            { updateProductUseCase(updatedProduct) },
            { _uiState.update { it.copy(finishAndGoBack = true) } },
            {}
        )
    }
}