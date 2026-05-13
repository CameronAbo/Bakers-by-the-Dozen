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
fun ConversionDemoDialog(
    recipe: Recipe,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Baker Percentage Converter")
        },
        text = {
            Column {
                Text("Recipe: ${recipe.title}")

                Spacer(modifier = Modifier.height(12.dp))

                Text("Base flour amount: 500g")
                Text("Hydration: 70%")
                Text("Salt: 2%")
                Text("Yeast: 1%")

                Spacer(modifier = Modifier.height(12.dp))

                Text("Converted Ingredients:")
                Text("Flour: 500g")
                Text("Water: 350g")
                Text("Salt: 10g")
                Text("Yeast: 5g")

                Spacer(modifier = Modifier.height(12.dp))

                Text("Example concept:")
                Text("Water = 500g × 70% = 350g")
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Done")
            }
        }
    )
}
