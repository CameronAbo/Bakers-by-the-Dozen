package com.example.bbd_prototype.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bbd_prototype.components.BottomBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "saved_recipes",
            modifier = Modifier.padding(padding)
        ) {
            composable("saved_recipes") {
                SavedRecipesScreen()
            }

            composable("explore") {
                ExploreScreen()
            }
        }
    }
}
