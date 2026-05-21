package com.mmfsin.tnt.domain.mappers

import com.mmfsin.tnt.domain.models.CategoryType.Companion.getNoneCategory
import com.mmfsin.tnt.domain.models.Product
import java.util.UUID

fun createProductFromDefault(name: String, haveIt: Boolean, favorite: Boolean) =
    Product(
        id = UUID.randomUUID().toString(),
        name = name,
        whereToFind = null,
        info = null,
        haveIt = haveIt,
        favorite = favorite,
        category = getNoneCategory(),
        date = System.currentTimeMillis()
    )