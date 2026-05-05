package com.mmfsin.tnt.domain.interfaces

import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface IDataRepository {
    fun getAllProducts(): Flow<List<Product>>
    fun addSingleProduct(name: String)

    suspend fun updateHaveIt(productId: String, haveIt: Boolean)
}