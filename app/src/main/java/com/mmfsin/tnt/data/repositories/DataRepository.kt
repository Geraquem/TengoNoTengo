package com.mmfsin.tnt.data.repositories

import com.mmfsin.tnt.data.bbdd.daos.ProductsDAO
import com.mmfsin.tnt.data.mappers.createSingleProduct
import com.mmfsin.tnt.data.mappers.toProductList
import com.mmfsin.tnt.domain.interfaces.IDataRepository
import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.presentation.utils.sortedByFilter
import javax.inject.Inject

class DataRepository @Inject constructor(
    val productsDao: ProductsDAO
) : IDataRepository {

    override fun getAllProducts(filter: FilterType): List<Product> {
        return productsDao.getAllProducts().sortedByFilter(filter).toProductList()
    }

    override fun addSingleProduct(name: String) {
        productsDao.insertProduct(createSingleProduct(name))
    }
}