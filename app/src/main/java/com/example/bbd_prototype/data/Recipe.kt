package com.example.bbd_prototype.data

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: String,
    val shareLink: String = "https://recipeapp.demo/share/recipe"
)