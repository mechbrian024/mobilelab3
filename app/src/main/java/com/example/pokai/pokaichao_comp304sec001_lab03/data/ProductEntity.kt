package com.example.pokai.pokaichao_comp304sec001_lab03.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val price: Double,
    val dateOfDelivery: String,
    val category: String,
    @ColumnInfo(defaultValue = "0")
    val favorite: Boolean
)