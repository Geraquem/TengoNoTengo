package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.models.Category
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getCategories
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor() {
    operator fun invoke(): List<Category> = getCategories()
}