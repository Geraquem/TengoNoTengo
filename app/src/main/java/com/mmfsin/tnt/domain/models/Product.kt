package com.mmfsin.tnt.domain.models

data class Product(
    val id: String,
    val name: String,
    val info: String,
    val whereToFind: String,
    val haveIt: Boolean,
    val favorite: Boolean,
)
