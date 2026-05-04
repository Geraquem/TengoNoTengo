package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.HomeItem
import com.mmfsin.tnt.domain.models.HomeType
import com.mmfsin.tnt.domain.models.HomeType.HAVE
import javax.inject.Inject

class GetHomeItemsUseCase @Inject constructor() {
    operator fun invoke(): List<HomeItem> = getItems()
}

fun getItems() = listOf(
    HomeItem(
        type = HomeType.MY_PRODUCTS,
        icon = R.drawable.ic_arrow_back,
        name = R.string.home_box_my_products
    ),
    HomeItem(
        type = HomeType.DONT_HAVE,
        icon = R.drawable.ic_arrow_back,
        name = R.string.home_box_dont_have
    ),
    HomeItem(
        type = HAVE,
        icon = R.drawable.ic_arrow_back,
        name = R.string.home_box_have
    ),
    HomeItem(
        type = HomeType.FAVORITES,
        icon = R.drawable.ic_arrow_back,
        name = R.string.home_box_favorites
    )
)

