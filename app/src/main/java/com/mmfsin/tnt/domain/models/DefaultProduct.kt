package com.mmfsin.tnt.domain.models

data class DefaultProduct(
    val name: String,
    val exists: Boolean = false,
    val haveIt: Boolean = false,
    val favorite: Boolean = false
)

fun getDefaultExamples() = listOf(
    DefaultProduct(name = "Manzanas"),
    DefaultProduct(name = "Soja texturizada"),
    DefaultProduct(name = "Leche", exists = true),
    DefaultProduct(name = "Avena"),
)