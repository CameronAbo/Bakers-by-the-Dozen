package com.example.bbd_prototype.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bbd_prototype.components.RecipeCard
import com.example.bbd_prototype.components.dialogs.ConversionDemoDialog
import com.example.bbd_prototype.components.dialogs.RecipeDialog
import com.example.bbd_prototype.components.dialogs.ShareRecipeDialog
import com.example.bbd_prototype.data.Recipe
import com.example.bbd_prototype.data.RecipeRepository



@Composable
fun SavedRecipesScreen( onRecipeClick: (Int) -> Unit ) {
    var recipes by remember { mutableStateOf(RecipeRepository.savedRecipes.toList()) }
    var recipeForConversion by remember { mutableStateOf<Recipe?>(null) }
    var recipeForSharing by remember { mutableStateOf<Recipe?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var recipeBeingEdited by remember { mutableStateOf<Recipe?>(null) }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Saved Recipes",
                style = MaterialTheme.typography.headlineMedium
            )

            Button(
                onClick = {
                    recipeBeingEdited = null
                    showDialog = true
                }
            ) {
                Text("Add")
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(recipes, key = { it.id }) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = {
                        onRecipeClick(recipe.id)
                    },
                    onEdit = {
                        recipeBeingEdited = recipe
                        showDialog = true
                    },
                    onDelete = {
                        recipes = recipes.filter { it.id != recipe.id }
                    },
                    onConvert = {
                        recipeForConversion = recipe
                    },
                    onShare = {
                        recipeForSharing = recipe
                    }
                )
            }
        }
    }

    if (showDialog) {
        RecipeDialog(
            recipe = recipeBeingEdited,
            onDismiss = {
                showDialog = false
            },
            onSave = { savedRecipe ->
                recipes = if (recipeBeingEdited == null) {
                    val newId = (recipes.maxOfOrNull { it.id } ?: 0) + 1
                    recipes + savedRecipe.copy(id = newId)
                } else {
                    recipes.map {
                        if (it.id == savedRecipe.id) savedRecipe else it
                    }
                }

                showDialog = false
            }
        )
    }
    recipeForConversion?.let { recipe ->
        ConversionDemoDialog(
            recipe = recipe,
            onDismiss = { recipeForConversion = null }
        )
    }

    recipeForSharing?.let { recipe ->
        ShareRecipeDialog(
            recipe = recipe,
            onDismiss = { recipeForSharing = null }
        )
    }
}