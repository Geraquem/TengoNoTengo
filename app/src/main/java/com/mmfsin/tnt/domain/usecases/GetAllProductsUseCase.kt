package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IDataRepository
import com.mmfsin.tnt.domain.models.Product
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: IDataRepository
) {
    operator fun invoke(): List<Product> = repository.getAllProducts()
}