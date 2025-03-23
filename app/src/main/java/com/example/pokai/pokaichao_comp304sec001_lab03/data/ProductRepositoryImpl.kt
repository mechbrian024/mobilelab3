package com.example.pokai.pokaichao_comp304sec001_lab03.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val productAPI: ProductAPI,
    private val dispatcher: CoroutineDispatcher,
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun getProducts(): Flow<List<Product>> {
        return productDao.getAllProducts().map { productCachedList ->
            productCachedList.map { productEntity ->
                Product(
                    id = productEntity.id,
                    name = productEntity.name,
                    price = productEntity.price,
                    dateOfDelivery = productEntity.dateOfDelivery.toString(),
                    category = productEntity.category,
                    favorite = productEntity.favorite
                )
            }
        }.flowOn(dispatcher)
    }

    override suspend fun fetechRemoteProducts() {
        withContext(dispatcher) {
            val response = productAPI.getProducts("all") // or use empty string / dynamic tag
            if (response.isSuccessful) {
                response.body()!!.map {
                    productDao.insert(
                        ProductEntity(
                            id = it.id,
                            name = it.name,
                            price = it.price,
                            dateOfDelivery = it.dateOfDelivery.toString(),
                            category = it.category,
                            favorite = it.favorite
                        )
                    )
                }
            }

        }
    }

    override suspend fun updateProduct(product: Product) {
        withContext(dispatcher) {
            productDao.update(
                ProductEntity(
                    id = product.id,
                    name = product.name,
                    price = product.price,
                    dateOfDelivery = product.dateOfDelivery,
                    category = product.category,
                    favorite = product.favorite
                )
            )
        }
    }

    override suspend fun getFavoriteProducts(): Flow<List<Product>> {
        return productDao.getFavoriteProducts().map { entities ->
            entities.map { entity ->
                Product(
                    id = entity.id,
                    name = entity.name,
                    price = entity.price,
                    dateOfDelivery = entity.dateOfDelivery,
                    category = entity.category,
                    favorite = entity.favorite
                )
            }
        }.flowOn(dispatcher)
    }
}