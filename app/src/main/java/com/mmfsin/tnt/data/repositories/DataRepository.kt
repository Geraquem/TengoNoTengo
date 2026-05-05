package com.mmfsin.tnt.data.repositories

import com.mmfsin.tnt.data.bbdd.daos.ProductsDAO
import com.mmfsin.tnt.data.mappers.createSingleProduct
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

    override fun addSingleProduct(name: String) {
        productsDao.insertProduct(createSingleProduct(name))
    }

    override suspend fun updateHaveIt(productId: String, haveIt: Boolean) {
        productsDao.updateHaveIt(productId, haveIt)
    }
}