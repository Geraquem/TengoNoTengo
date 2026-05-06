package com.mmfsin.tnt.presentation.productdetail

import com.mmfsin.tnt.domain.models.Product

data class ProductDetailStates(
    val product: Product? = null,

    val productId: String = "",
    val newName: String = "",
    val newInfo: String = "",
    val newWhereTo: String = "",
    val haveIt: Boolean = false,
    val isFavorite: Boolean = false,

    val deleteDialog: Boolean = false,
    val finishAndGoBack: Boolean = false,

    val sww: Boolean = false,
)
