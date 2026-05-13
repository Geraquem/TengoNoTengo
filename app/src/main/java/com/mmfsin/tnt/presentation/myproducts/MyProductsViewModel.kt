package com.mmfsin.tnt.presentation.myproducts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.FilterType.Companion.getFilterById
import com.mmfsin.tnt.domain.models.HomeTypeClassification.BY_CATEGORIES
import com.mmfsin.tnt.domain.models.HomeTypeClassification.Companion.getClassificationById
import com.mmfsin.tnt.domain.models.HomeTypeClassification.Companion.getEmptyMessageById
import com.mmfsin.tnt.domain.models.HomeTypeClassification.Companion.getTitleById
import com.mmfsin.tnt.domain.models.HomeTypeClassification.DONT_HAVE
import com.mmfsin.tnt.domain.models.HomeTypeClassification.FAVORITES
import com.mmfsin.tnt.domain.models.HomeTypeClassification.HAVE
import com.mmfsin.tnt.domain.models.HomeTypeClassification.MY_PRODUCTS
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.domain.usecases.AddSingleProductUseCase
import com.mmfsin.tnt.domain.usecases.GetActualFilterUseCase
import com.mmfsin.tnt.domain.usecases.GetAddProductVisibleUseCase
import com.mmfsin.tnt.domain.usecases.GetAllProductsUseCase
import com.mmfsin.tnt.domain.usecases.GetFavoriteProductsUseCase
import com.mmfsin.tnt.domain.usecases.GetProductsByCategoryUseCase
import com.mmfsin.tnt.domain.usecases.GetProductsByHaveItUseCase
import com.mmfsin.tnt.domain.usecases.UpdateAddProductVisibleUseCase
import com.mmfsin.tnt.domain.usecases.UpdateFilterUseCase
import com.mmfsin.tnt.domain.usecases.UpdateHaveProductUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import com.mmfsin.tnt.presentation.utils.sortedByFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProductsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductsByHaveItUseCase: GetProductsByHaveItUseCase,
    private val getFavoriteProductUseCase: GetFavoriteProductsUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val getAddProductVisibleUseCase: GetAddProductVisibleUseCase,
    private val addSingleProductUseCase: AddSingleProductUseCase,
    private val updateAddProductVisibleUseCase: UpdateAddProductVisibleUseCase,
    private val getActualFilterUseCase: GetActualFilterUseCase,
    private val updateFilterUseCase: UpdateFilterUseCase,
    private val updateHaveProductUseCase: UpdateHaveProductUseCase,
) : BaseViewModel<MyProductsStates>(MyProductsStates()) {

    private val filterFlow = MutableStateFlow(FilterType.ALPHABETIC)

    private val typeClassification: Int? = savedStateHandle["classification"]

    init {
        showLoading()
        getAddProductVisible()
        getActualFilter()
        observeProducts()
        getTitle()
        getEmptyMessage()
    }

    private fun showLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun observeProducts() {
        viewModelScope.launch {
            combine(
                getPertinentProducts(),
                filterFlow
            ) { products, filter ->
                if (_uiState.value.byCategories) products
                else products.sortedByFilter(filter)
            }.collect { sortedProducts ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        products = sortedProducts
                    )
                }
            }
        }
    }

    private fun getActualFilter() {
        executeUseCase(
            { getActualFilterUseCase() },
            { actualFilter ->
                filterFlow.value = actualFilter
                _uiState.update { it.copy(actualFilter = actualFilter) }
            },
            {}
        )
    }

    private fun getPertinentProducts(): Flow<List<Product>> {
        return if (typeClassification == null) getAllProductsUseCase()
        else when (getClassificationById(typeClassification)) {
            MY_PRODUCTS -> getAllProductsUseCase()
            DONT_HAVE -> getProductsByHaveItUseCase(haveIt = false)
            HAVE -> getProductsByHaveItUseCase(haveIt = true)
            FAVORITES -> getFavoriteProductUseCase()
            BY_CATEGORIES -> {
                _uiState.update { it.copy(byCategories = true) }
                getProductsByCategoryUseCase()
            }
        }
    }

    private fun getTitle() {
        val title = getTitleById(typeClassification)
        _uiState.update { it.copy(title = title) }
    }

    private fun getEmptyMessage() {
        val emptyMessage = getEmptyMessageById(typeClassification)
        _uiState.update { it.copy(emptyMessage = emptyMessage) }
    }

    private fun getAddProductVisible() {
        executeUseCase(
            { getAddProductVisibleUseCase() },
            { visible -> _uiState.update { it.copy(productToAddVisible = visible) } },
            {}
        )
    }

    fun onProductToAddChange(value: String) {
        _uiState.update { it.copy(productToAdd = value) }
    }

    fun addSingleProduct(name: String) {
        executeUseCase(
            { addSingleProductUseCase(name) },
            {
                _uiState.update { it.copy(productToAdd = "", clearKeyboard = true) }
            },
            {}
        )
    }

    fun updateClearKeyboard() = _uiState.update { it.copy(clearKeyboard = false) }

    fun changeAddProductVisibility() {
        executeUseCase(
            { updateAddProductVisibleUseCase(!_uiState.value.productToAddVisible) },
            { getAddProductVisible() },
            {}
        )
    }

    fun updateFilterDialogVisibility() = _uiState.update { it.copy(filterDialogVisible = !it.filterDialogVisible) }

    fun updateFilterType(id: Int) {
        val newFilter = getFilterById(id)

        executeUseCase(
            { updateFilterUseCase(id) },
            {
                filterFlow.value = newFilter
                _uiState.update { it.copy(actualFilter = newFilter) }
                updateFilterDialogVisibility()
            },
            {}
        )
    }

    fun updateHaveProduct(productId: String, haveIt: Boolean) {
        executeUseCase(
            { updateHaveProductUseCase(productId, haveIt) },
            { },
            {}
        )
    }
}