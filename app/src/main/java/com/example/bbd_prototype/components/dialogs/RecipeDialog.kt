package com.example.bbd_prototype.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bbd_prototype.data.Recipe

@Composable
fun RecipeDialog(
    recipe: Recipe?,
    onDismiss: () -> Unit,
    onSave: (Recipe) -> Unit
) {
    var title by remember { mutableStateOf(recipe?.title ?: "") }
    var description by remember { mutableStateOf(recipe?.description ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(if (recipe == null) "Add Recipe" else "Edit Recipe")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Recipe Title") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank()) {
                        onSave(
                            Recipe(
                                id = recipe?.id ?: 0,
                                title = title,
                                description = description,
                                ingredients = recipe?.ingredients ?: listOf(
                                    "Example ingredient 1",
                                    "Example ingredient 2"
                                ),
                                instructions = recipe?.instructions ?: "Example cooking instructions."
                            )
                        )
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
