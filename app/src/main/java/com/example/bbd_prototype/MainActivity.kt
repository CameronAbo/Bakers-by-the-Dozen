package com.example.bbd_prototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: String,
    val shareLink: String = "https://recipeapp.demo/share/recipe"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("main") {
            MainScreen()
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bakers by the Dozen",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onLoginSuccess) {
            Text("Login")
        }
    }
}

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

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("saved_recipes")
            },
            label = {
                Text("Saved")
            },
            icon = {}
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("explore")
            },
            label = {
                Text("Explore")
            },
            icon = {}
        )
    }
}

@Composable
fun SavedRecipesScreen() {
    var recipes by remember {
        mutableStateOf(
            listOf(
                Recipe(1,
                    "Neapolitan Pizza Dough",
                    "Classic Pizza Dough meant for a wood fired oven",
                    listOf("Flour 950 g",
                                        "Chilled Water 545 g",
                                        "Dry Active Yeast 1.5 g",
                                        "Sea Salt 24 g"),
                    "Pour the salt into the water and stir until the salt has dissolved. " +
                                 "Add about half of the flour and mix until the mix is even. Proceed to add the dry yeast "+
                                 "and the rest of the flour. Mix for 15 to 20 minutes until the dough forms into a ball with a smooth surface.")
            )
        )
    }

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

@Composable
fun RecipeCard(
    recipe: Recipe,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onConvert: () -> Unit,
    onShare: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Button(onClick = onConvert) {
                    Text("Convert")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = onShare) {
                    Text("Share")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(onClick = onEdit) {
                    Text("Edit")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    }
}

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

@Composable
fun ExploreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Explore Recipes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("This screen can later show recipe suggestions or API results.")
    }
}

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