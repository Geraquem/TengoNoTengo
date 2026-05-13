package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.HomeItem
import com.mmfsin.tnt.domain.models.HomeTypeClassification
import com.mmfsin.tnt.domain.models.HomeTypeClassification.HAVE
import com.mmfsin.tnt.presentation.core.components.SwitchFavorite
import com.mmfsin.tnt.presentation.core.components.SwitchHaveIt
import com.mmfsin.tnt.presentation.home.components.CategoryIcon
import javax.inject.Inject

class GetHomeItemsUseCase @Inject constructor() {
    operator fun invoke(): List<HomeItem> = getItems().sortedBy { it.order }
}

fun getItems() = listOf(
    HomeItem(
        type = HAVE,
        icon = { onClick -> SwitchHaveIt(true) { onClick() } },
        name = R.string.home_box_have,
        order = 0
    ),
    HomeItem(
        type = HomeTypeClassification.DONT_HAVE,
        icon = { onClick -> SwitchHaveIt(false) { onClick() } },
        name = R.string.home_box_dont_have,
        order = 1
    ),
    HomeItem(
        type = HomeTypeClassification.FAVORITES,
        icon = { onClick -> SwitchFavorite(true) { onClick() } },
        name = R.string.home_box_favorites,
        order = 2
    ),
    HomeItem(
        type = HomeTypeClassification.BY_CATEGORIES,
        icon = { CategoryIcon() },
        name = R.string.home_box_by_categories,
        order = 3
    )
)

