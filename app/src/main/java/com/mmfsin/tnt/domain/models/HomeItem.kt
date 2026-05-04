package com.mmfsin.tnt.domain.models

data class HomeItem(
    val type: HomeType,
    val icon: Int,
    val name: Int
)

enum class HomeType {
    MY_PRODUCTS,
    DONT_HAVE,
    HAVE,
    FAVORITES
}
