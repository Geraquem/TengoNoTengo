package com.mmfsin.tnt.domain.models

data class Product(
    val id: String,
    val name: String,
    val info: String?,
    val whereToFind: String?,
    val haveIt: Boolean,
    val favorite: Boolean,
    val date: Long,
)

fun getExampleProducts() = listOf(
    Product(
        id = "",
        name = "Producto 1",
        info = null,
        whereToFind = null,
        haveIt = true,
        favorite = false,
        date = 0
    ),
    Product(
        id = "",
        name = "Producto 2",
        info = "Muy rico en proteína",
        whereToFind = null,
        haveIt = false,
        favorite = false,
        date = 0
    ),
    Product(
        id = "",
        name = "Producto 3",
        info = null,
        whereToFind = "Mercadona",
        haveIt = true,
        favorite = true,
        date = 0
    ),
    Product(
        id = "",
        name = "Producto 4",
        info = "A veces me sienta mal",
        whereToFind = "Alcampo",
        haveIt = false,
        favorite = false,
        date = 0
    ),
)