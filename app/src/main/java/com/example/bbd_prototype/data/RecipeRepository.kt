package com.example.bbd_prototype.data

object RecipeRepository {

    val savedRecipes = mutableListOf(
        Recipe(
            id = 1,
            title = "Neapolitan Pizza Dough",
            description = "Classic Pizza Dough meant for a wood fired oven",
            ingredients = listOf(
                "Flour 950 g",
                "Chilled Water 545 g",
                "Dry Active Yeast 1.5 g",
                "Sea Salt 24 g"
            ),
            instructions = "Pour the salt into the water and stir until dissolved. Add half of the flour and mix. Add the dry yeast and the rest of the flour. Mix for 15 to 20 minutes until smooth."
        )
    )

    val exploreRecipes = mutableListOf(
        Recipe(
            id = 101,
            title = "Chocolate Chip Cookies",
            description = "Soft cookies with crisp edges.",
            ingredients = listOf(
                "Flour 300 g",
                "Butter 225 g",
                "Brown Sugar 200 g",
                "Chocolate Chips 250 g",
                "Eggs 2"
            ),
            instructions = "Cream butter and sugar. Add eggs. Mix in flour and chocolate chips. Bake at 350°F for 10-12 minutes."
        )
    )
}