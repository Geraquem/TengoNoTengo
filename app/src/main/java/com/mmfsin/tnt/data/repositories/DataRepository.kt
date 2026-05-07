package com.mmfsin.tnt.data.repositories

import com.mmfsin.tnt.data.bbdd.daos.ProductsDAO
import com.mmfsin.tnt.data.mappers.createSingleProduct
import com.mmfsin.tnt.data.mappers.toProduct
import com.mmfsin.tnt.data.mappers.toProductDTO
import com.mmfsin.tnt.data.mappers.toProductList
import com.mmfsin.tnt.domain.interfaces.IDataRepository
import com.mmfsin.tnt.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataRepository @Inject constructor(
    val productsDao: ProductsDAO
) : IDataRepository {

    override fun getAllProducts(): Flow<List<Product>> {
        return productsDao.getAllProducts().map { list -> list.toProductList() }
    }

    override fun getProductById(id: String): Product? {
        return productsDao.getProductById(id)?.toProduct()
    }

    override fun addSingleProduct(name: String) {
        productsDao.insertProduct(createSingleProduct(name))
    }

    override fun addAdvancedProduct(product: Product) {
        productsDao.insertProduct(product.toProductDTO())
    }

    override suspend fun updateProduct(product: Product) {
        productsDao.updateProduct(
            id = product.id,
            name = product.name,
            info = product.info,
            whereTo = product.whereToFind,
            haveIt = product.haveIt,
            favorite = product.favorite
        )
    }

    override suspend fun updateHaveIt(productId: String, haveIt: Boolean) {
        productsDao.updateHaveIt(productId, haveIt)
    }

    override suspend fun updateFavoriteIt(productId: String, isFavorite: Boolean) {
        productsDao.updateFavorite(productId, isFavorite)
    }

    override suspend fun updateCategory(productId: String, categoryId: Int) {
        productsDao.updateCategory(productId, categoryId)
    }

    override suspend fun deleteProduct(productId: String) {
        productsDao.deleteProductById(productId)
    }
}