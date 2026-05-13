package com.mmfsin.tnt.presentation.createproduct

import com.mmfsin.tnt.domain.models.Category
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.domain.models.createEmptyProduct

data class CreateAdvancedProductStates(
    val product: Product = createEmptyProduct(),

    val categories: List<Category> = emptyList(),
    val categoriesState: Boolean = false,

    val finishAndGoBack: Boolean = false,
    val sww: Boolean = false,
)
