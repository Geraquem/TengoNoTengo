package com.mmfsin.tnt.domain.models

import androidx.compose.runtime.Composable
import com.mmfsin.tnt.R

data class HomeItem(
    val type: HomeTypeClassification,
    val icon: @Composable (onClick: () -> Unit) -> Unit,
    val name: Int,
    val order: Int
)

enum class HomeTypeClassification(val id: Int, val title: Int, val emptyMessage: Int) {
    MY_PRODUCTS(0, title = R.string.home_box_my_products, emptyMessage = R.string.my_products_nothing_added),
    DONT_HAVE(1, title = R.string.classification_dont_have_title, emptyMessage = R.string.classification_dont_have_empty),
    HAVE(2, title = R.string.classification_have_title, emptyMessage = R.string.classification_have_empty),
    FAVORITES(3, title = R.string.classification_favorites_title, emptyMessage = R.string.classification_favorites_empty),
    BY_CATEGORIES(4, title = R.string.classification_categories_title, emptyMessage = R.string.classification_categories_empty);

    companion object {
        fun getClassificationById(id: Int): HomeTypeClassification {
            return entries.firstOrNull { it.id == id } ?: MY_PRODUCTS
        }

        fun getTitleById(id: Int?): Int = entries.firstOrNull { it.id == id }?.title ?: R.string.empty
        fun getEmptyMessageById(id: Int?): Int = entries.firstOrNull { it.id == id }?.emptyMessage ?: R.string.my_products_nothing_added
    }
}
