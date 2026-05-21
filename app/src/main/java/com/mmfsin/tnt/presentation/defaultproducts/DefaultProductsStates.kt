package com.mmfsin.tnt.presentation.defaultproducts

import com.mmfsin.tnt.domain.models.DefaultProduct

data class DefaultProductsStates(
    val products: List<DefaultProduct> = emptyList(),

    val addSheetVisibility: Boolean = false,
    val defaultProductSelected: DefaultProduct? = null,
)
