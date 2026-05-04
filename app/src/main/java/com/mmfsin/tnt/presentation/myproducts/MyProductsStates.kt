package com.mmfsin.tnt.presentation.myproducts

import com.mmfsin.tnt.domain.models.Product

data class MyProductsStates(
    val products: List<Product> = emptyList(),

    val productToAdd: String = ""
)
