package com.mmfsin.tnt.domain.models

import java.util.UUID

data class Product(
    val id: String,
    val name: String,
    val info: String?,
    val whereToFind: String?,
    val haveIt: Boolean,
    val favorite: Boolean,
    val category: Category?,
    val date: Long,
)

fun createEmptyProduct() = Product(
    id = UUID.randomUUID().toString(),
    name = "",
    info = null,
    whereToFind = null,
    haveIt = false,
    favorite = false,
    category = null,
    date = System.nanoTime()
)

fun getExampleProducts() = listOf(
    Product(
        id = "id1",
        name = "Producto 1",
        info = null,
        whereToFind = null,
        haveIt = true,
        favorite = false,
        category = null,
        date = 0
    ),
    Product(
        id = "id2",
        name = "Producto 2",
        info = "Muy rico en proteína",
        whereToFind = null,
        haveIt = false,
        favorite = false,
        category = null,
        date = 0
    ),
    Product(
        id = "id3",
        name = "Producto 3",
        info = null,
        whereToFind = "Mercadona",
        haveIt = true,
        favorite = true,
        category = null,
        date = 0
    ),
    Product(
        id = "id4",
        name = "Producto 4",
        info = "A veces me sienta mal",
        whereToFind = "Alcampo",
        haveIt = false,
        favorite = false,
        category = null,
        date = 0
    ),
)