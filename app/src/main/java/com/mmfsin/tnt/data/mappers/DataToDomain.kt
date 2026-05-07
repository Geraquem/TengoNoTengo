package com.mmfsin.tnt.data.mappers

import com.mmfsin.tnt.data.ProductDTO
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getCategoryById
import com.mmfsin.tnt.domain.models.Product
import java.util.UUID

fun ProductDTO.toProduct() = Product(
    id = id,
    name = name,
    info = info,
    whereToFind = whereToFind,
    haveIt = haveIt,
    favorite = favorite,
    category = getCategoryById(categoryId),
    date = date
)

fun List<ProductDTO>.toProductList() = this.map { it.toProduct() }

fun createSingleProduct(name: String) = ProductDTO(
    id = UUID.randomUUID().toString(),
    name = name,
    date = System.currentTimeMillis()
)