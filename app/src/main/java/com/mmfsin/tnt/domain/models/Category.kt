package com.mmfsin.tnt.domain.models

import androidx.compose.ui.graphics.Color
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.theme.Black
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.core.theme.GrayMedium

data class Category(
    val id: Int,
    val name: Int,
    val icon: Int?,
    val color: Color,
    val type: CategoryType
)

enum class CategoryType(val id: Int, val categoryName: Int, val icon: Int?, val color: Color) {
    SNACKS(
        id = 1,
        categoryName = R.string.category_snacks,
        icon = R.drawable.ic_category_snacks,
        color = Color(0xFFE5B763)
    ),

    BASICS(
        id = 2,
        categoryName = R.string.category_basics,
        icon = R.drawable.ic_category_basic,
        color = Color(0xFFCED485)
    ),

    DRINKS(
        id = 3,
        categoryName = R.string.category_drinks,
        icon = R.drawable.ic_category_drinks,
        color = Color(0xFF6BBCCE)
    ),

    BAKERY(
        id = 4,
        categoryName = R.string.category_bakery,
        icon = R.drawable.ic_category_biscuit,
        color = Color(0xFFE0BC7F)
    ),

    MEAT(
        id = 5,
        categoryName = R.string.category_meat,
        icon = R.drawable.ic_category_meat,
        color = Color(0xFFAD6A5F)
    ),

    CEREALS(
        id = 6,
        categoryName = R.string.category_cereals,
        icon = R.drawable.ic_category_cereal,
        color = Color(0xFFE7AD73)
    ),

    FROZEN(
        id = 7,
        categoryName = R.string.category_frozen,
        icon = R.drawable.ic_category_frozen,
        color = Color(0xFF65DEEA)
    ),

    CAPRICHOS(
        id = 8,
        categoryName = R.string.category_caprichos,
        icon = R.drawable.ic_category_caprichos,
        color = Color(0xFF91E88A)
    ),

    BREAKFAST(
        id = 9,
        categoryName = R.string.category_breakfast,
        icon = R.drawable.ic_category_breakfast,
        color = Color(0xFFF8CB62)
    ),

    EMBUTIDOS(
        id = 10,
        categoryName = R.string.category_cold_cuts,
        icon = R.drawable.ic_category_embutido,
        color = Color(0xFFCCA5EA)
    ),

    SPICES(
        id = 11,
        categoryName = R.string.category_spices,
        icon = R.drawable.ic_category_spices,
        color = Color(0xFFB7BD6F)
    ),

    FRUIT(
        id = 12,
        categoryName = R.string.category_fruit,
        icon = R.drawable.ic_category_fruit,
        color = Color(0xFFFA7A7A)
    ),

    NUTS(
        id = 13,
        categoryName = R.string.category_nuts,
        icon = R.drawable.ic_category_nuts,
        color = Color(0xFFCE9346)
    ),

    LACTEOS(
        id = 14,
        categoryName = R.string.category_dairy,
        icon = R.drawable.ic_category_lacteos,
        color = Color(0xFFB7BD6F)
    ),

    LEGUMES(
        id = 15,
        categoryName = R.string.category_legumes,
        icon = R.drawable.ic_category_legumes,
        color = Color(0xFFE8EEA3)
    ),

    BREAD(
        id = 16,
        categoryName = R.string.category_bread,
        icon = R.drawable.ic_category_bread,
        color = Color(0xFFB7BD6F)
    ),

    PASTA(
        id = 17,
        categoryName = R.string.category_pasta,
        icon = R.drawable.ic_category_pasta,
        color = Color(0xFFBABBB4)
    ),

    FISH(
        id = 18,
        categoryName = R.string.category_fish,
        icon = R.drawable.ic_category_fish,
        color = Color(0xFF7BBFE2)
    ),

    DESSERTS(
        id = 19,
        categoryName = R.string.category_desserts,
        icon = R.drawable.ic_category_dessert,
        color = Color(0xFFB7BD6F)
    ),

    CHEESE(
        id = 20,
        categoryName = R.string.category_cheese,
        icon = R.drawable.ic_category_cheese,
        color = Color(0xFFFFFBAC)
    ),

    SAUCES(
        id = 21,
        categoryName = R.string.category_sauces,
        icon = R.drawable.ic_category_sauces,
        color = Color(0xFF9FD4C8)
    ),

    GLUTEN_FREE(
        id = 22,
        categoryName = R.string.category_gluten_free,
        icon = R.drawable.ic_category_no_gluten,
        color = Color(0xFFB7BD6F)
    ),

    LACTOSE_FREE(
        id = 23,
        categoryName = R.string.category_lactose_free,
        icon = R.drawable.ic_category_no_lactose,
        color = Color(0xFFA39E9E)
    ),

    SOUPS_AND_CREAMS(
        id = 24,
        categoryName = R.string.category_soups_and_creams,
        icon = R.drawable.ic_category_soup,
        color = Color(0xFF84D9B4)
    ),

    VEGAN(
        id = 25,
        categoryName = R.string.category_vegan,
        icon = R.drawable.ic_category_vegan,
        color = Color(0xFFB7BD6F)
    ),

    VEGETARIAN(
        id = 26,
        categoryName = R.string.category_vegetarian,
        icon = R.drawable.ic_category_vegetarian,
        color = Color(0xFFB7BD6F)
    ),

    VEGETABLES(
        id = 27,
        categoryName = R.string.category_vegetables,
        icon = R.drawable.ic_category_vegetables,
        color = Color(0xFF81CD78)
    ),
    NONE(
        id = 99,
        categoryName = R.string.category_none,
        icon = null,
        color = GrayMedium
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