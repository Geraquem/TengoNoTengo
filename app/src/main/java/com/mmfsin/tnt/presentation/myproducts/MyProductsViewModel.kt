package com.mmfsin.tnt.presentation.myproducts

import androidx.lifecycle.viewModelScope
import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.FilterType.Companion.getFilterById
import com.mmfsin.tnt.domain.usecases.AddSingleProductUseCase
import com.mmfsin.tnt.domain.usecases.GetActualFilterUseCase
import com.mmfsin.tnt.domain.usecases.GetAddProductVisibleUseCase
import com.mmfsin.tnt.domain.usecases.GetAllProductsUseCase
import com.mmfsin.tnt.domain.usecases.UpdateAddProductVisibleUseCase
import com.mmfsin.tnt.domain.usecases.UpdateFilterUseCase
import com.mmfsin.tnt.domain.usecases.UpdateHaveProductUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import com.mmfsin.tnt.presentation.utils.sortedByFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getAddProductVisibleUseCase: GetAddProductVisibleUseCase,
    private val addSingleProductUseCase: AddSingleProductUseCase,
    private val updateAddProductVisibleUseCase: UpdateAddProductVisibleUseCase,
    private val getActualFilterUseCase: GetActualFilterUseCase,
    private val updateFilterUseCase: UpdateFilterUseCase,
    private val updateHaveProductUseCase: UpdateHaveProductUseCase,
) : BaseViewModel<MyProductsStates>(MyProductsStates()) {

    private val filterFlow = MutableStateFlow(FilterType.ALPHABETIC)

    init {
        getAddProductVisible()
        getActualFilter()
        observeProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            combine(
                getAllProductsUseCase(),
                filterFlow
            ) { products, filter ->
                products.sortedByFilter(filter)
            }.collect { sortedProducts -> _uiState.update { it.copy(products = sortedProducts) } }
        }
    }

    private fun getAddProductVisible() {
        executeUseCase(
            { getAddProductVisibleUseCase() },
            { visible -> _uiState.update { it.copy(productToAddVisible = visible) } },
            {}
        )
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

    fun updateFilterDialogVisibility() = _uiState.update { it.copy(showFilterDialog = !it.showFilterDialog) }

    fun updateFilterType(id: Int) {
        val newFilter = getFilterById(id)

        executeUseCase(
            { updateFilterUseCase(id) },
            {
                filterFlow.value = newFilter
                updateFilterDialogVisibility()
            },
            {}
        )
    }

    fun updateHaveProduct(productId: String, haveIt: Boolean) {
        executeUseCase(
            { updateHaveProductUseCase(productId, haveIt) },
            { /**???????*/ },
            {}
        )
    }
}