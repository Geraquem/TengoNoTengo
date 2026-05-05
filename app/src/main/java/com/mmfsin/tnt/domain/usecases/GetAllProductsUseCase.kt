package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IConfigRepository
import com.mmfsin.tnt.domain.interfaces.IDataRepository
import com.mmfsin.tnt.domain.models.FilterType.Companion.getFilterById
import com.mmfsin.tnt.domain.models.Product
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val dataRepository: IDataRepository,
    private val configRepository: IConfigRepository,
) {
    operator fun invoke(): List<Product> {
        val filterId = configRepository.getActualFilter()
        return dataRepository.getAllProducts(getFilterById(filterId))
    }
}