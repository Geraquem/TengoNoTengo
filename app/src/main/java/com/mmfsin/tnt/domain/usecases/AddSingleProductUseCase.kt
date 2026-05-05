package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IDataRepository
import javax.inject.Inject

class AddSingleProductUseCase @Inject constructor(
    private val repository: IDataRepository
) {
    operator fun invoke(name: String) = repository.addSingleProduct(name)
}