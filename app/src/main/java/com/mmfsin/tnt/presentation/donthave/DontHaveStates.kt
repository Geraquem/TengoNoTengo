package com.mmfsin.tnt.presentation.donthave

import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.Product

data class DontHaveStates(
    val isLoading: Boolean = false,

    val products: List<Product> = emptyList(),

    val filterDialogVisible: Boolean = false,
    val actualFilter: FilterType? = null,
)
