package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IConfigRepository
import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.FilterType.Companion.getFilterById
import javax.inject.Inject

class GetActualFilterUseCase @Inject constructor(
    private val repository: IConfigRepository
) {
    operator fun invoke(): FilterType = getFilterById(repository.getActualFilter())
}