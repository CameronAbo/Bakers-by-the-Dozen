package com.example.bbd_prototype.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bbd_prototype.data.Recipe

@Composable
fun ShareRecipeDialog(
    recipe: Recipe,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Share Recipe")
        },
        text = {
            Column {
                Text("Share this recipe using a link:")

                Spacer(modifier = Modifier.height(12.dp))

                Text(recipe.shareLink + "/${recipe.id}")

                Spacer(modifier = Modifier.height(12.dp))

                Text("In the full version, this would generate a real shareable recipe page.")
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Copy Link")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}