package com.kkm.recetas.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class LocalRecipes(
    @PrimaryKey val id: Int,
    val name: String,
    val imageThumb: String,
    val videoThumb: String,
    val area: String,
    val category: String,
    val instructions: String,
    val ingredients: List<String>,
    val measures: List<String>
)
