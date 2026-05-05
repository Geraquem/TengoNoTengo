package com.mmfsin.tnt.domain.interfaces

import com.mmfsin.tnt.domain.models.Product

interface IDataRepository {
    fun getAllProducts(): List<Product>

    fun addSingleProduct(name: String)
}