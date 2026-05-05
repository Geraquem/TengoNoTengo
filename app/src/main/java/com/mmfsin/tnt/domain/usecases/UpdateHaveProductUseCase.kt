package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IDataRepository
import javax.inject.Inject

class UpdateHaveProductUseCase @Inject constructor(
    private val repository: IDataRepository
) {
    suspend operator fun invoke(productId: String, haveIt: Boolean) = repository.updateHaveIt(productId, haveIt)
}