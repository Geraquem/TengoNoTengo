package com.mmfsin.tnt.domain.models

import androidx.compose.ui.graphics.Color
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.theme.GreenMedium

data class Category(
    val id: Int,
    val name: Int,
    val icon: Int,
    val color: Color,
    val type: CategoryType
)

enum class CategoryType(val id: Int, val categoryName: Int, val icon: Int, val color: Color) {
    NONE(
        id = 0,
        categoryName = R.string.category_none,
        icon = R.drawable.ic_arrow_down,
        color = GreenMedium
    ),

    FRUITS(
        id = 1,
        categoryName = R.string.category_fruits,
        icon = R.drawable.ic_arrow_down,
        color = GreenMedium
    ),

    VEGETABLES(
        id = 2,
        categoryName = R.string.category_vegetables,
        icon = R.drawable.ic_arrow_down,
        color = GreenMedium
    ),

    MEAT(
        id = 3,
        categoryName = R.string.category_meat,
        icon = R.drawable.ic_arrow_down,
        color = GreenMedium
    );

    companion object {
        fun selectNoneCategory(): Category = NONE.toCategory()
        fun getCategories(): List<Category> = entries.toCategoryList()
        fun getCategoryById(id: Int): Category = (entries.firstOrNull { it.id == id } ?: NONE).toCategory()
    }
}

fun CategoryType.toCategory() = Category(
    id = id,
    name = categoryName,
    icon = icon,
    color = color,
    type = this
)

fun List<CategoryType>.toCategoryList() = this.map { it.toCategory() }