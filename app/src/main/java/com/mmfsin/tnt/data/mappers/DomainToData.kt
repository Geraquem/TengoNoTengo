package com.mmfsin.tnt.data.mappers

import com.mmfsin.tnt.data.ProductDTO
import com.mmfsin.tnt.domain.models.Product

fun Product.toProductDTO() = ProductDTO(
    id = id,
    name = name,
    info = info,
    whereToFind = whereToFind,
    haveIt = haveIt,
    favorite = favorite,
    date = date
)