package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IDataRepository
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor(
    private val repository: IDataRepository
) {
    suspend operator fun invoke(productId: String, categoryId: Int) =
        repository.updateCategory(productId, categoryId)
}