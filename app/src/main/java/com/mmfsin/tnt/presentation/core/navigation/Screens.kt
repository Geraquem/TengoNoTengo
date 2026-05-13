package com.mmfsin.tnt.presentation.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class MyProducts(val classification: Int)

@Serializable
data class ProductDetail(val id: String)

@Serializable
data class CreateAdvancedProduct(val name: String)