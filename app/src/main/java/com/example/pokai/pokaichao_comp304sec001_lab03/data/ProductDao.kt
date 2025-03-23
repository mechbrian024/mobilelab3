package com.example.pokai.pokaichao_comp304sec001_lab03.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): Flow<List<ProductEntity>>

    @Update
    suspend fun update(productEntity: ProductEntity)

    @Delete
    suspend fun delete(productEntity: ProductEntity)

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductById(productId: Int): ProductEntity

    @Query("SELECT * FROM products WHERE favorite = 1")
    suspend fun getFavoriteProducts(): Flow<List<ProductEntity>>

}