package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IConfigRepository
import javax.inject.Inject

class UpdateAddProductVisibleUseCase @Inject constructor(
    private val repository: IConfigRepository
) {
    operator fun invoke(visible: Boolean) = repository.updateAddProductVisible(visible)
}