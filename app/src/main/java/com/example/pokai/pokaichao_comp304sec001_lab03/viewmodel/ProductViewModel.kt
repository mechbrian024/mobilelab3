package com.example.pokai.pokaichao_comp304sec001_lab03.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.util.PrimitiveIterator.OfDouble

data class Product(
    val id: Int,
    val name: String,
    //price should always be positive
    val price: Double,
    var dateOfDelivery: LocalDate,
    var category: String,
    val favorite: Boolean
){

}

class ProductViewModel(initialProducts: List<Product> = emptyList()):   ViewModel() {

    private val _products = mutableStateListOf<Product>().apply { addAll(initialProducts)}
    public val products: List<Product> get() = _products

    fun addProduct(product: Product){
        _products.add(product)
    }

    fun removeProduct(product: Product){
        _products.remove(product)
    }

    fun updateProduct(product: Product) {
        val index = _products.indexOfFirst { it.id == product.id }
        if (index != -1) {
            _products[index] = product
        }
    }
}