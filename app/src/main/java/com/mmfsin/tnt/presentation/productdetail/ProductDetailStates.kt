package com.mmfsin.tnt.presentation.productdetail

import com.mmfsin.tnt.domain.models.Category
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getNoneCategory
import com.mmfsin.tnt.domain.models.Product

data class ProductDetailStates(
    val oldProduct: Product? = null,

    val productId: String = "",
    val newName: String = "",
    val newInfo: String = "",
    val newWhereToFind: String = "",
    val newHaveIt: Boolean = false,
    val newFavorite: Boolean = false,
    val newCategory: Category = getNoneCategory(),

    val categories: List<Category> = emptyList(),
    val categoriesState: Boolean = false,

    val deleteDialog: Boolean = false,
    val finishAndGoBack: Boolean = false,

    val sww: Boolean = false,
)
