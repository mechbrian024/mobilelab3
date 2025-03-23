package com.example.pokai.pokaichao_comp304sec001_lab03.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductAPI {
    @GET("products")
    suspend fun getProducts(
        @Query("tag") tag: String
    ): Response<List<Product>>
}