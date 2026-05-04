package com.mmfsin.tnt.data.mappers

import com.mmfsin.tnt.data.ProductDTO
import com.mmfsin.tnt.domain.models.Product

fun ProductDTO.toProduct() = Product(
    id = id,
    name = name,
    info = info,
    whereToFind = whereToFind,
    haveIt = haveIt,
    favorite = favorite
)

fun List<ProductDTO>.toProductList() = this.map { it.toProduct() }