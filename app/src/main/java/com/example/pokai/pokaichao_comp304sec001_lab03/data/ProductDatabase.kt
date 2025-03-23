package com.example.pokai.pokaichao_comp304sec001_lab03.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [ProductEntity::class],
    version = 1,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)

@TypeConverters(ProductTypeConverters::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}