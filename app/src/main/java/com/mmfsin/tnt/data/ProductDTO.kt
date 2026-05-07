package com.mmfsin.tnt.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mmfsin.tnt.presentation.utils.TABLE_PRODUCTS

@Entity(tableName = TABLE_PRODUCTS)
data class ProductDTO(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val info: String? = null,
    val whereToFind: String? = null,
    val haveIt: Boolean = false,
    val favorite: Boolean = false,
    val categoryId: Int = 0,
    val date: Long = 0,
)
