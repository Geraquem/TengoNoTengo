package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDefaultProductsUseCase @Inject constructor(
    private val repository: IDataRepository,
) {
    operator fun invoke(): Flow<List<String>> = repository.getDefaultProducts()
}