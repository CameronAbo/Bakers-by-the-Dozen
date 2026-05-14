package com.example.bbd_prototype.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bbd_prototype.R

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


data class Post(
    val id: Int,
    val author: String,
    val authorPfpRes: Int,
    val description: String,
    val likes: Int,
    val tags: List<String> = emptyList(),
    val imageRes: Int
)

@Composable
fun ExploreScreen() {
    var savedRecipeName by androidx.compose.runtime.remember{
        androidx.compose.runtime.mutableStateOf<String?>(null)
    }
    val dummyPosts = listOf(
        Post(1, "C-Bread", R.drawable.pfp_example_image1, "Cinnamon Quick Bread\nCinnamon quick bread can be eaten as an indulgent breakfast, a cozy midday treat or a warming dessert. \n-Ingredients: Flour, Sugar, Buttermilk, Cinnamon", 124, listOf("Cinnamon", "Baking", "Bread"), R.drawable.post_example_image1),
        Post(2, "CookieMan", R.drawable.pfp_example_image2, "Lemon Pound Cake Muffins\nThese sweet, zingy lemon pound cake muffins are like decadent, moist cakes in individual portions. Eat them like muffins, serve them with fruit, anything goes!\n-Ingredients: Butter, Sugar, Eggs, Sour cream, Vanilla Extract, Lemon Extract, Flour, Salt, Baking Soda, Lemon Juice", 89, listOf("Muffin", "PoundCake", "LemonTaste"), R.drawable.post_example_image2),
        Post(3, "PastryGuyDude", R.drawable.pfp_example_image3, "Pumpkin Scones\nThese pumpkin scones are tender and moist, making them a truly delightful fall breakfast treat.\n-Ingredients: Flour, Brown Sugar, Baking Powder, Pumpkin Pie Spice, Ground Cinnamon, Baking Soda, Salt, Butter, Large Eggs, Canned Pumpking, 2% Milk", 256, listOf("Pumpkin", "Pastry"), R.drawable.post_example_image3),
        Post(4, "PizzaButIfPastry", R.drawable.pfp_example_image4, "Banana Beignets\nThese mini beignets are so easy and so good, you may find yourself buying extra bananas on purpose.\n-Ingredients: Sugar, Brown Sugar, Cinnamon, Cake Flour, Baking Powder, Egg, Bananas, Milk, Canola Oil", 412, listOf("Beignet", "Snack", "Bite-Sized"), R.drawable.post_example_image4)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Explore Recipes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(dummyPosts) { post ->
                PostCard(post = post)

                Button(
                    onClick = {
                        val recipeToSave = com.example.bbd_prototype.data.Recipe(
                            id = post.id + 100,
                            title = post.description.substringBefore("\n"),
                            description = post.description.substringAfter("\n").substringBefore("-Ingredients:"),
                            ingredients = post.description
                                .substringAfter("-Ingredients:")
                                .split(",")
                                .map { it.trim() },
                            instructions = "Saved from Explore Recipes."
                        )
                        if (com.example.bbd_prototype.data.RecipeRepository.savedRecipes.none { it.id == recipeToSave.id }) {
                            com.example.bbd_prototype.data.RecipeRepository.savedRecipes.add(recipeToSave)
                        }
                        savedRecipeName = recipeToSave.title
                    },
                    modifier = Modifier
                        .padding(start = 12.dp, bottom = 12.dp)
                ) {
                    Text("Save Recipe")
            }
        }
    }
}

    if (savedRecipeName != null) {
        AlertDialog(
            onDismissRequest = {
                savedRecipeName = null
            },
            title = {
                Text("Recipe Saved")
            },
            text = {
                Text("$savedRecipeName has been added to your saved recipes.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        savedRecipeName = null
                    }
                ) {
                    Text("Done")
                }
            }
        )
    }
}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Picture
                Image(
                    painter = painterResource(id = post.authorPfpRes),
                    contentDescription = "Author profile picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = post.author,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Image
            Image(
                painter = painterResource(id = post.imageRes),
                contentDescription = "Post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            // Actions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Like action */ }) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Like"
                    )
                }
                Text(
                    text = "${post.likes}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.width(16.dp))

                IconButton(onClick = { /* Repost action */ }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Repost"
                    )
                }
                Text(
                    text = "Repost",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Tags
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                post.tags.forEach { tag ->
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        tonalElevation = 2.dp
                    ) {
                        Text(
                            text = "#$tag",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }

            // Description
            Text(
                text = post.description,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
