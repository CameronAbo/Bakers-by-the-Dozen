package com.example.bbd_prototype.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bbd_prototype.components.BottomBar
import com.example.bbd_prototype.data.RecipeRepository
import com.example.bbd_prototype.screens.RecipeDetailScreen

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
                SavedRecipesScreen(
                    onRecipeClick = { recipeId ->
                        navController.navigate("recipe_detail/$recipeId")
                    }
                )
            }

            composable("explore") {
                ExploreScreen()
            }

            composable("recipe_detail/{recipeId}") { backStackEntry ->
                val recipeId = backStackEntry.arguments
                    ?.getString("recipeId")
                    ?.toIntOrNull()

                val recipe = RecipeRepository.savedRecipes.find { it.id == recipeId }

                if (recipe != null) {
                    RecipeDetailScreen(
                        recipe = recipe,
                        onBack = {
                            navController.popBackStack()
                        },
                        onSaveEdit = { updatedRecipe ->
                            val index = RecipeRepository.savedRecipes.indexOfFirst {
                                it.id == updatedRecipe.id
                            }

                            if (index != -1) {
                                RecipeRepository.savedRecipes[index] = updatedRecipe
                            }

                            navController.popBackStack()
                        },
                        onCreateIteration = { iteration ->
                            RecipeRepository.savedRecipes.add(iteration)
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
