package com.mmfsin.tnt.data.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmfsin.tnt.data.models.ProductDTO
import com.mmfsin.tnt.data.bbdd.daos.ProductsDAO

@Database(entities = [ProductDTO::class], version = 1)
abstract class RoomConfiguration : RoomDatabase() {
    abstract fun productsDAO(): ProductsDAO
}