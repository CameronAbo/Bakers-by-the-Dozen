package com.example.bbd_prototype.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bbd_prototype.data.Recipe


@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    onBack: () -> Unit,
    onSaveEdit: (Recipe) -> Unit,
    onCreateIteration: (Recipe) -> Unit
) {
    val focusManager = LocalFocusManager.current

    var title by remember { mutableStateOf(recipe.title) }
    var description by remember { mutableStateOf(recipe.description) }
    var instructions by remember { mutableStateOf(recipe.instructions) }

    val ingredientsText = recipe.ingredients.joinToString("\n")
    var ingredients by remember { mutableStateOf(ingredientsText) }

    var flourAmount by remember { mutableStateOf("500") }
    var hydrationPercent by remember { mutableStateOf("70") }

    val flour = flourAmount.toDoubleOrNull() ?: 0.0
    val hydration = hydrationPercent.toDoubleOrNull() ?: 0.0
    val waterAmount = flour * (hydration / 100.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            }
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Button(onClick = onBack) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Recipe Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Recipe Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = ingredients,
            onValueChange = { ingredients = it },
            label = { Text("Ingredients") },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = instructions,
            onValueChange = { instructions = it },
            label = { Text("Instructions") },
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Unit Conversion Demo",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = flourAmount,
            onValueChange = { flourAmount = it },
            label = { Text("Flour Amount in grams") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = hydrationPercent,
            onValueChange = { hydrationPercent = it },
            label = { Text("Hydration Percentage") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Water = ${"%.1f".format(waterAmount)} g")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val updatedRecipe = recipe.copy(
                    title = title,
                    description = description,
                    ingredients = ingredients.lines().filter { it.isNotBlank() },
                    instructions = instructions
                )

                onSaveEdit(updatedRecipe)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val iteration = recipe.copy(
                    id = recipe.id + 1000,
                    title = "$title - Iteration",
                    description = description,
                    ingredients = ingredients.lines().filter { it.isNotBlank() },
                    instructions = instructions
                )

                onCreateIteration(iteration)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Recipe Iteration")
        }
    }
}