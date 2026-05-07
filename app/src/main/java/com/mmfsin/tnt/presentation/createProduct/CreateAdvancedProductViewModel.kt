package com.mmfsin.tnt.presentation.createProduct

import androidx.lifecycle.SavedStateHandle
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getCategoryById
import com.mmfsin.tnt.domain.usecases.AddAdvancedProductUseCase
import com.mmfsin.tnt.domain.usecases.GetCategoriesUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateAdvancedProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val addAdvancedProductUseCase: AddAdvancedProductUseCase
) : BaseViewModel<CreateAdvancedProductStates>(CreateAdvancedProductStates()) {

    private val productName: String? = savedStateHandle["name"]

    init {
        onNameChanged(productName ?: "")
        getCategories()
    }

    fun onNameChanged(value: String) = _uiState.update { it.copy(product = it.product.copy(name = value)) }
    fun onWhereToFindChanged(value: String) = _uiState.update { it.copy(product = it.product.copy(whereToFind = value)) }
    fun onInfoChanged(value: String) = _uiState.update { it.copy(product = it.product.copy(info = value)) }
    fun updateHaveIt(value: Boolean) = _uiState.update { it.copy(product = it.product.copy(haveIt = value)) }
    fun updateFavorite(value: Boolean) = _uiState.update { it.copy(product = it.product.copy(favorite = value)) }
    fun updateCategoriesState() = _uiState.update { it.copy(categoriesState = !it.categoriesState) }

    fun getCategories() {
        executeUseCase(
            { getCategoriesUseCase() },
            { categories -> _uiState.update { it.copy(categories = categories) } },
            {}
        )
    }

    fun updateCategory(categoryId: Int) {
        _uiState.update { it.copy(product = it.product.copy(category = getCategoryById(categoryId))) }
        updateCategoriesState()
    }

    fun createProduct() {
        val newProduct = _uiState.value.product
        executeUseCase(
            { addAdvancedProductUseCase(newProduct) },
            { _uiState.update { it.copy(finishAndGoBack = true) } },
            {}
        )
    }
}