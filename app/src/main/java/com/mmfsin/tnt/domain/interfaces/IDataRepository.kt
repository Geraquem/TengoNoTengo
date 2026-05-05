package com.mmfsin.tnt.domain.interfaces

import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.Product

interface IDataRepository {
    fun getAllProducts(filter: FilterType): List<Product>
    fun addSingleProduct(name: String)
}