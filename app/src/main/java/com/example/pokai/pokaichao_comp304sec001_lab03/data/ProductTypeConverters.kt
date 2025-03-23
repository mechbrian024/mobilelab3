package com.example.pokai.pokaichao_comp304sec001_lab03.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProductTypeConverters {
    @TypeConverter
    fun convertTagsToString(tags: List<String>): String {
        return Json.encodeToString(tags)
    }

    @TypeConverter
    fun convertStringToTags(tags: String): List<String> {
        return Json.decodeFromString(tags)
    }
}