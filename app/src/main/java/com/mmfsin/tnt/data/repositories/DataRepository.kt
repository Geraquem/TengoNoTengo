package com.mmfsin.tnt.data.repositories

import com.mmfsin.tnt.data.bbdd.daos.ProductsDAO
import com.mmfsin.tnt.data.mappers.toProductList
import com.mmfsin.tnt.domain.interfaces.IDataRepository
import com.mmfsin.tnt.domain.models.Product
import javax.inject.Inject

class DataRepository @Inject constructor(
    val productsDao: ProductsDAO
) : IDataRepository {

    override fun getAllProducts(): List<Product> {
        return productsDao.getAllProducts().toProductList()
    }
}