package com.mmfsin.tnt.presentation.defaultproducts.helper

import com.mmfsin.tnt.domain.models.DefaultProduct
import com.mmfsin.tnt.domain.models.Product

fun createDefaultList(defaultProducts: List<String>, products: List<Product>): List<DefaultProduct> {

    val productMap = products.associateBy { it.name }

    return defaultProducts.map { name ->
        val product = productMap[name]
        DefaultProduct(
            name = name,
            exists = product != null,
            haveIt = product?.haveIt ?: false,
            favorite = product?.favorite ?: false
        )
    }
}