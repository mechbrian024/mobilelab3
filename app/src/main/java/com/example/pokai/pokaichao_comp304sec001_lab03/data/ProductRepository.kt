package com.example.pokai.pokaichao_comp304sec001_lab03.data

import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<List<Product>>
    suspend fun fetechRemoteProducts();
    suspend fun updateProduct(product: Product)
    suspend fun getFavoriteProducts(): Flow<List<Product>>

}