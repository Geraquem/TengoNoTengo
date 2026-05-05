package com.mmfsin.tnt.presentation.myproducts

import com.mmfsin.tnt.domain.usecases.AddSingleProductUseCase
import com.mmfsin.tnt.domain.usecases.GetActualFilterUseCase
import com.mmfsin.tnt.domain.usecases.GetAddProductVisibleUseCase
import com.mmfsin.tnt.domain.usecases.GetAllProductsUseCase
import com.mmfsin.tnt.domain.usecases.UpdateAddProductVisibleUseCase
import com.mmfsin.tnt.domain.usecases.UpdateFilterUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getAddProductVisibleUseCase: GetAddProductVisibleUseCase,
    private val addSingleProductUseCase: AddSingleProductUseCase,
    private val updateAddProductVisibleUseCase: UpdateAddProductVisibleUseCase,
    private val getActualFilterUseCase: GetActualFilterUseCase,
    private val updateFilterUseCase: UpdateFilterUseCase,
) : BaseViewModel<MyProductsStates>(MyProductsStates()) {

    init {
        getAddProductVisible()
        getActualFilter()
    }

    private fun getMyProducts() {
        executeUseCase(
            { getAllProductsUseCase() },
            { products -> _uiState.update { it.copy(products = products) } },
            {},
        )
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
            { filter ->
                _uiState.update { it.copy(actualFilter = filter) }
                getMyProducts()
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
                getMyProducts()
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
        executeUseCase(
            { updateFilterUseCase(id) },
            {
                updateFilterDialogVisibility()
                getActualFilter()
            },
            {}
        )
    }
}