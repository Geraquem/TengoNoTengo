package com.mmfsin.tnt.presentation.myproducts

import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.Product

data class MyProductsStates(
    val products: List<Product> = emptyList(),

    val productToAdd: String = "",
    val productToAddVisible: Boolean = true,

    val clearKeyboard: Boolean = false,

    val filterDialogVisible: Boolean = false,
    val actualFilter: FilterType? = null,
)
