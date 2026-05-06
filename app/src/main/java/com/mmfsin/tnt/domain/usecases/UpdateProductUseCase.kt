package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IDataRepository
import com.mmfsin.tnt.domain.models.Product
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val repository: IDataRepository
) {
    suspend operator fun invoke(newProduct: Product) = repository.updateProduct(newProduct)
}