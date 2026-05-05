package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IConfigRepository
import javax.inject.Inject

class GetAddProductVisibleUseCase @Inject constructor(
    private val repository: IConfigRepository
) {
    operator fun invoke(): Boolean = repository.getAddProductVisible()
}