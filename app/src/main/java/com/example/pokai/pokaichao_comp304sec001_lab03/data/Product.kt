package com.example.pokai.pokaichao_comp304sec001_lab03.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Product(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("name")
    val name: String = "",

    @SerialName("price")
    val price: Double = 0.0,

    @SerialName("dateOfDelivery")
    val dateOfDelivery: String = "",

    @SerialName("category")
    val category: String,

    @SerialName("favorite")
    var favorite: Boolean = false
)