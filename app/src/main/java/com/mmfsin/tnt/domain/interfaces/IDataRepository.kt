package com.mmfsin.tnt.domain.interfaces

import com.mmfsin.tnt.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface IDataRepository {
    fun getAllProducts(): Flow<List<Product>>
    fun getProductById(id: String): Product?
    fun getProductsIDontHave(): Flow<List<Product>>
    fun getProductsIHave(): Flow<List<Product>>
    fun getFavoriteProducts(): Flow<List<Product>>

    fun addSingleProduct(name: String)
    fun addAdvancedProduct(product: Product)

    suspend fun updateProduct(product: Product)
    suspend fun updateHaveIt(productId: String, haveIt: Boolean)
    suspend fun updateFavoriteIt(productId: String, isFavorite: Boolean)
    suspend fun updateCategory(productId: String, categoryId: Int)
    suspend fun deleteProduct(productId: String)

    fun getDefaultProducts(): Flow<List<String>>
}