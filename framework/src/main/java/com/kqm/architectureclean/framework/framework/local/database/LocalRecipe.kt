package com.kqm.architectureclean.framework.framework.local.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "recipes")
data class LocalRecipe(
    @PrimaryKey val id: String,
    val name: String,
    val imageThumb: String,
    val videoThumb: String,
    val area: String,
    val category: String,
    val instructions: String,
    val isFavorite: Boolean
)

@Entity(tableName = "ingredients")
data class LocalIngredient(
    @PrimaryKey val name: String
)

@Entity(tableName = "measures")
data class LocalMeasure(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val recipeId: String,
    val ingredientId: String,
    val measure: String
)

data class RecipeWithIngredientsAndMeasures(
    @Embedded val recipe: LocalRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "name"
    )
    val ingredients: List<LocalIngredient>,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val measures: List<LocalMeasure>
)
