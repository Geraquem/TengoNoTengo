package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IConfigRepository
import javax.inject.Inject

class UpdateFilterUseCase @Inject constructor(
    private val repository: IConfigRepository
) {
    operator fun invoke(id: Int) = repository.updateActualFilter(id)
}