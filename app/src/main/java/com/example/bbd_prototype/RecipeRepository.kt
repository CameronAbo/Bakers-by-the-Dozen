package com.example.bbd_prototype

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object RecipeRepository {

    private val savedRecipes = mutableListOf<Recipe>()
    private val exploreRecipes = mutableListOf<Recipe>()

    fun loadRecipes(context: Context) {
        if (savedRecipes.isEmpty()) {
            savedRecipes.addAll(loadFromRaw(context, R.raw.saved_recipes))
        }

        if (exploreRecipes.isEmpty()) {
            exploreRecipes.addAll(loadFromRaw(context, R.raw.explore_recipes))
        }
    }

    fun getSavedRecipes(): MutableList<Recipe> {
        return savedRecipes
    }

    fun getExploreRecipes(): MutableList<Recipe> {
        return exploreRecipes
    }

    fun addCustomRecipe(recipe: Recipe) {
        savedRecipes.add(recipe)
    }

    fun saveRecipeFromExplore(recipe: Recipe) {
        if (savedRecipes.none { it.id == recipe.id }) {
            savedRecipes.add(recipe)
        }
    }

    private fun loadFromRaw(context: Context, rawId: Int): List<Recipe> {
        val json = context.resources.openRawResource(rawId)
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<Recipe>>() {}.type
        return Gson().fromJson(json, type)
    }
}