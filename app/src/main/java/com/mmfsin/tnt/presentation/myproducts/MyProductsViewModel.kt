package com.mmfsin.tnt.presentation.myproducts

import com.mmfsin.tnt.domain.usecases.GetAllProductsUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : BaseViewModel<MyProductsStates>(MyProductsStates()) {

    init {
        getMyProducts()
    }

    private fun getMyProducts() {
        executeUseCase(
            { getAllProductsUseCase() },
            { products ->
                _uiState.update {
                    it.copy(
                        products = products
                    )
                }
            },
            {},
        )
    }
}