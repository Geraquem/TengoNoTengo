package com.mmfsin.tnt.domain.models

import androidx.compose.runtime.Composable
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.components.SwitchFavorite
import com.mmfsin.tnt.presentation.core.components.SwitchHaveIt
import com.mmfsin.tnt.presentation.home.components.CategoryIcon

data class HomeItem(
    val id: Int,
    val icon: @Composable (onClick: () -> Unit) -> Unit,
    val title: Int,
    val emptyMessage: Int,
    val pngBackground: Int
)

enum class HomeTypeClassification(
    val id: Int,
    val icon: @Composable (onClick: () -> Unit) -> Unit,
    val title: Int,
    val emptyMessage: Int,
    val pngBackground: Int
) {
    MY_PRODUCTS(
        id = 0,
        icon = {},
        title = R.string.home_box_my_products,
        emptyMessage = R.string.my_products_nothing_added,
        pngBackground = R.drawable.png_spaguetti
    ),
    HAVE(
        id = 1,
        icon = { onClick -> SwitchHaveIt(true) { onClick() } },
        title = R.string.classification_have_title,
        emptyMessage = R.string.classification_have_empty,
        pngBackground = R.drawable.png_kiwis
    ),
    DONT_HAVE(
        id = 2,
        icon = { onClick -> SwitchHaveIt(false) { onClick() } },
        title = R.string.classification_dont_have_title,
        emptyMessage = R.string.classification_dont_have_empty,
        pngBackground = R.drawable.png_blueberry
    ),

    FAVORITES(
        id = 3,
        icon = { onClick -> SwitchFavorite(true) { onClick() } },
        title = R.string.classification_favorites_title,
        emptyMessage = R.string.classification_favorites_empty,
        pngBackground = R.drawable.png_lemon
    ),
    BY_CATEGORIES(
        id = 4,
        icon = { CategoryIcon() },
        title = R.string.classification_categories_title,
        emptyMessage = R.string.classification_categories_empty,
        pngBackground = R.drawable.png_strawberry
    );

    companion object {
        fun getClassificationById(id: Int): HomeTypeClassification = entries.firstOrNull { it.id == id } ?: MY_PRODUCTS

        fun getHomeItems(): List<HomeItem> = entries.filter { it.id != MY_PRODUCTS.id }.toHomeItemList()

        fun getTitleById(id: Int?): Int = entries.firstOrNull { it.id == id }?.title ?: R.string.empty
        fun getEmptyMessageById(id: Int?): Int = entries.firstOrNull { it.id == id }?.emptyMessage ?: R.string.my_products_nothing_added
    }
}

fun HomeTypeClassification.toHomeItem() = HomeItem(
    id = id,
    icon = icon,
    title = title,
    emptyMessage = emptyMessage,
    pngBackground = pngBackground
)

fun List<HomeTypeClassification>.toHomeItemList() = this.map { it.toHomeItem() }
