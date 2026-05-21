package com.mmfsin.tnt.presentation.defaultproducts

import androidx.lifecycle.viewModelScope
import com.mmfsin.tnt.domain.models.DefaultProduct
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.domain.usecases.AddAdvancedProductUseCase
import com.mmfsin.tnt.domain.usecases.GetAllProductsUseCase
import com.mmfsin.tnt.domain.usecases.GetDefaultProductsUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import com.mmfsin.tnt.presentation.defaultproducts.helper.createDefaultList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DefaultProductsViewModel @Inject constructor(
    private val getDefaultProductsUseCase: GetDefaultProductsUseCase,
    private val getAllProductsUseCaseUseCase: GetAllProductsUseCase,
    private val addAdvancedProductUseCase: AddAdvancedProductUseCase
) : BaseViewModel<DefaultProductsStates>(DefaultProductsStates()) {

    init {
        observeProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            combine(
                getDefaultProductsUseCase(),
                getAllProductsUseCaseUseCase(),
            ) { defaultProducts, myProducts ->
                createDefaultList(defaultProducts, myProducts)
            }.collect { products ->
                _uiState.update {
                    it.copy(
                        products = products
                    )
                }
            }
        }
    }

    fun changeAddSheetVisibility() {
        val actualState = uiState.value.addSheetVisibility
        _uiState.update { it.copy(addSheetVisibility = !actualState) }
    }

    fun showAddSheet(defaultProduct: DefaultProduct) {
        _uiState.update {
            it.copy(
                defaultProductSelected = defaultProduct,
                addSheetVisibility = true
            )
        }
    }

    fun addProduct(product: Product) {
        executeUseCase(
            { addAdvancedProductUseCase(product) },
            { changeAddSheetVisibility() },
            {}
        )
    }
}