package com.example.bbd_prototype.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bbd_prototype.components.RecipeCard
import com.example.bbd_prototype.components.dialogs.RecipeDialog
import com.example.bbd_prototype.data.RecipeRepository



@Composable
fun SavedRecipesScreen( onRecipeClick: (Int) -> Unit ) {
    var recipes by remember { mutableStateOf(RecipeRepository.savedRecipes.toList()) }
    var showDialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 18.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Good morning, baker!",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Your saved recipes, perfected over time.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text("Search saved recipes...")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Saved Recipes",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Button(
                onClick = {
                    showDialog = true
                },
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("+ Add")
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
                    }
                )
            }
        }
    }

    if (showDialog) {
        RecipeDialog(
            recipe = null,
            onDismiss = {
                showDialog = false
            },
            onSave = { savedRecipe ->
                val newId = (recipes.maxOfOrNull { it.id } ?: 0) + 1
                recipes = recipes + savedRecipe.copy(id = newId)

                showDialog = false
            }
        )
    }
}