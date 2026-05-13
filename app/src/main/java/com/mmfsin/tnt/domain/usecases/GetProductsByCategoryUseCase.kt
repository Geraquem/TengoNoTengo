package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IDataRepository
import com.mmfsin.tnt.domain.models.FilterType.BY_CATEGORY
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.presentation.utils.sortedByFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: IDataRepository,
) {
    operator fun invoke(): Flow<List<Product>> {
        return repository.getAllProducts().map { list ->
            list.sortedByFilter(BY_CATEGORY)
        }
    }
}
