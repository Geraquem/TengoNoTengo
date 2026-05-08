package com.mmfsin.tnt.domain.models

import androidx.compose.ui.graphics.Color
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.theme.Black

data class Category(
    val id: Int,
    val name: Int,
    val icon: Int?,
    val color: Color,
    val type: CategoryType
)

enum class CategoryType(val id: Int, val categoryName: Int, val icon: Int?, val color: Color) {
    NONE(
        id = 0,
        categoryName = R.string.category_none,
        icon = null,
        color = Black
    ),

    SNACKS(
        id = 1,
        categoryName = R.string.category_snacks,
        icon = R.drawable.ic_category_snacks,
        color = Black
    ),

    BASICS(
        id = 2,
        categoryName = R.string.category_basics,
        icon = R.drawable.ic_category_basic,
        color = Black
    ),

    DRINKS(
        id = 3,
        categoryName = R.string.category_drinks,
        icon = R.drawable.ic_category_drinks,
        color = Black
    ),

    BAKERY(
        id = 4,
        categoryName = R.string.category_bakery,
        icon = R.drawable.ic_category_biscuit,
        color = Black
    ),

    MEAT(
        id = 5,
        categoryName = R.string.category_meat,
        icon = R.drawable.ic_category_meat,
        color = Black
    ),

    CEREALS(
        id = 6,
        categoryName = R.string.category_cereals,
        icon = R.drawable.ic_category_cereal,
        color = Black
    ),

    FROZEN(
        id = 7,
        categoryName = R.string.category_frozen,
        icon = R.drawable.ic_category_frozen,
        color = Black
    ),

    CAPRICHOS(
        id = 8,
        categoryName = R.string.category_caprichos,
        icon = R.drawable.ic_category_caprichos,
        color = Black
    ),

    BREAKFAST(
        id = 9,
        categoryName = R.string.category_breakfast,
        icon = R.drawable.ic_category_breakfast,
        color = Black
    ),

    EMBUTIDOS(
        id = 10,
        categoryName = R.string.category_cold_cuts,
        icon = R.drawable.ic_category_embutido,
        color = Black
    ),

    SPICES(
        id = 11,
        categoryName = R.string.category_spices,
        icon = R.drawable.ic_category_spices,
        color = Black
    ),

    FRUIT(
        id = 12,
        categoryName = R.string.category_fruit,
        icon = R.drawable.ic_category_fruit,
        color = Black
    ),

    NUTS(
        id = 13,
        categoryName = R.string.category_nuts,
        icon = R.drawable.ic_category_nuts,
        color = Black
    ),

    LACTEOS(
        id = 14,
        categoryName = R.string.category_dairy,
        icon = R.drawable.ic_category_lacteos,
        color = Black
    ),

    LEGUMES(
        id = 15,
        categoryName = R.string.category_legumes,
        icon = R.drawable.ic_category_legumes,
        color = Black
    ),

    BREAD(
        id = 16,
        categoryName = R.string.category_bread,
        icon = R.drawable.ic_category_bread,
        color = Black
    ),

    PASTA(
        id = 17,
        categoryName = R.string.category_pasta,
        icon = R.drawable.ic_category_pasta,
        color = Black
    ),

    FISH(
        id = 18,
        categoryName = R.string.category_fish,
        icon = R.drawable.ic_category_fish,
        color = Black
    ),

    DESSERTS(
        id = 19,
        categoryName = R.string.category_desserts,
        icon = R.drawable.ic_category_dessert,
        color = Black
    ),

    CHEESE(
        id = 20,
        categoryName = R.string.category_cheese,
        icon = R.drawable.ic_category_cheese,
        color = Black
    ),

    SAUCES(
        id = 21,
        categoryName = R.string.category_sauces,
        icon = R.drawable.ic_category_sauces,
        color = Black
    ),

    GLUTEN_FREE(
        id = 22,
        categoryName = R.string.category_gluten_free,
        icon = R.drawable.ic_category_no_gluten,
        color = Black
    ),

    LACTOSE_FREE(
        id = 23,
        categoryName = R.string.category_lactose_free,
        icon = R.drawable.ic_category_no_lactose,
        color = Black
    ),

    SOUPS_AND_CREAMS(
        id = 24,
        categoryName = R.string.category_soups_and_creams,
        icon = R.drawable.ic_category_soup,
        color = Black
    ),

    VEGAN(
        id = 25,
        categoryName = R.string.category_vegan,
        icon = R.drawable.ic_category_vegan,
        color = Black
    ),

    VEGETARIAN(
        id = 26,
        categoryName = R.string.category_vegetarian,
        icon = R.drawable.ic_category_vegetarian,
        color = Black
    ),

    VEGETABLES(
        id = 27,
        categoryName = R.string.category_vegetables,
        icon = R.drawable.ic_category_vegetables,
        color = Black
    );

    companion object {
        fun getNoneCategory(): Category = NONE.toCategory()
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