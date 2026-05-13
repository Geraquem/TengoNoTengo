package com.mmfsin.tnt.presentation.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object MyProducts

@Serializable
data class ProductDetail(val id: String)

@Serializable
data class CreateAdvancedProduct(val name: String)

@Serializable
object ProductsIDontHave

@Serializable
object ProductsIHave

@Serializable
object FavoriteProducts

@Serializable
object ProductsByCategories