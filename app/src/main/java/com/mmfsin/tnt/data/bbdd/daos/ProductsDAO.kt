package com.mmfsin.tnt.data.bbdd.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.mmfsin.tnt.data.ProductDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDAO {

    @Insert(onConflict = REPLACE)
    fun insertProduct(productDTO: ProductDTO)

    @Query("SELECT * FROM table_products")
    fun getAllProducts(): Flow<List<ProductDTO>>

    @Query("SELECT * FROM table_products WHERE id = :id LIMIT 1")
    fun getProductById(id: String): ProductDTO?

    @Query(
        """
        UPDATE table_products 
        SET 
        name = :name,
        info= :info,
        whereToFind = :whereTo,
        haveIt = :haveIt,
        favorite = :favorite
        WHERE id = :id
        """
    )
    suspend fun updateProduct(
        id: String,
        name: String,
        info: String?,
        whereTo: String?,
        haveIt: Boolean,
        favorite: Boolean
    )

    @Query(
        """
        UPDATE table_products 
        SET 
        haveIt = :haveIt
        WHERE id = :id
        """
    )
    suspend fun updateHaveIt(
        id: String,
        haveIt: Boolean
    )

    @Query(
        """
        UPDATE table_products 
        SET 
        favorite = :isFavorite
        WHERE id = :id
        """
    )
    suspend fun updateFavorite(
        id: String,
        isFavorite: Boolean
    )

    @Query("DELETE FROM table_products WHERE id = :id")
    suspend fun deleteProductById(id: String)
}