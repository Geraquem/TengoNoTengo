package com.mmfsin.tnt.domain.models

import androidx.compose.runtime.Composable

data class HomeItem(
    val type: HomeTypeClassification,
    val icon: @Composable () -> Unit,
    val name: Int,
    val order: Int
)

enum class HomeTypeClassification {
    MY_PRODUCTS,
    DONT_HAVE,
    HAVE,
    FAVORITES,
    BY_CATEGORIES
}
