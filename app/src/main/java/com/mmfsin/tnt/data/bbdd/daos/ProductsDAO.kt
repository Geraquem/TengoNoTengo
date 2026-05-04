package com.mmfsin.tnt.data.bbdd.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.mmfsin.tnt.data.ProductDTO

@Dao
interface ProductsDAO {

    @Insert(onConflict = REPLACE)
    fun insertProduct(productDTO: ProductDTO)

    @Query("SELECT * FROM table_products")
    fun getAllProducts()
}