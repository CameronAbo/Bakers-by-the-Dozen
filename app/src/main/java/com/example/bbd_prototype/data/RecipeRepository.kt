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
        ),
        Recipe(
            id = 102,
            title = "Classic Flan",
            description = "Creamy caramel custard dessert.",
            ingredients = listOf(
                "1 cup sugar",
                "1 Can condensed milk",
                "1 can evaporated milk",
                "2 cups heavy whipping cream",
                "1 tbsp vanilla extract",
                "4 eggs yolks",
                "3 eggs whites"
            ),
            instructions = "Melt sugar into caramel. Mix remaining ingredients. Pour into pan and bake in water bath at 350°F for 1.5 hours."
        ),
        Recipe(
            id = 103,
            title = "Bannana Bread",
            description = "Soft bannana bread with rich banana flavor.",
            ingredients = listOf(
                "3 ripe bananas",
                "2 cups of flour",
                "1 tsp baking soda",
                "3/4 cups brown sugar",
                "2 eggs",
                "1 tsp vanilla extract"
            ),
            instructions = "Mash bananas. Mix wet and dry ingredients separately, then combine. Pout into load pan and bake at 350°F for 60 minutes."
        ),
        Recipe(
    id = 104,
    title = "Tres Leches Cake",
    description = "Light sponge cake soaked in a sweet three milk mixture.",
    ingredients = listOf(
        "2 cups flour",
        "1 1/2 cups sugar",
        "1 cup milk",
        "5 eggs",
        "1 can condensed milk",
        "1 can evaporated milk",
        "1 cup heavy cream"
    ),
    instructions = "Bake sponge cake at 350°F for 30 minutes. Mix the three milks together and pour over cooled cake. Refrigerate before serving."
),

Recipe(
    id = 105,
    title = "Cinnamon Rolls",
    description = "Soft homemade cinnamon rolls with cream cheese frosting.",
    ingredients = listOf(
        "4 cups flour",
        "1 packet yeast",
        "1 cup warm milk",
        "1/3 cup butter",
        "1/2 cup sugar",
        "2 tbsp cinnamon",
        "1 egg"
    ),
    instructions = "Prepare dough and let rise for 1 hour. Roll out dough, spread butter and cinnamon sugar, then roll and slice. Bake at 350°F for 25 minutes."
),

Recipe(
    id = 106,
    title = "Red Velvet Cupcakes",
    description = "Classic red velvet cupcakes with cream cheese frosting.",
    ingredients = listOf(
        "2 cups flour",
        "1 cup sugar",
        "1 tbsp cocoa powder",
        "1 cup buttermilk",
        "2 eggs",
        "1/2 cup butter",
        "1 tsp red food coloring"
    ),
    instructions = "Mix ingredients until smooth. Fill cupcake liners and bake at 350°F for 18-20 minutes. Cool before frosting."
),

Recipe(
    id = 107,
    title = "Cheesecake",
    description = "Creamy baked cheesecake with a buttery graham cracker crust.",
    ingredients = listOf(
        "2 cups graham cracker crumbs",
        "1/2 cup melted butter",
        "3 blocks cream cheese",
        "1 cup sugar",
        "3 eggs",
        "1 tsp vanilla extract",
        "1/2 cup sour cream"
    ),
    instructions = "Prepare crust and press into pan. Mix filling ingredients until smooth and pour over crust. Bake at 325°F for 50-60 minutes and chill overnight."
),

Recipe(
    id = 108,
    title = "Tiramisu",
    description = "Classic Italian dessert layered with coffee-soaked ladyfingers and mascarpone cream.",
    ingredients = listOf(
        "2 packs ladyfingers",
        "1 cup espresso",
        "16 oz mascarpone cheese",
        "1 cup heavy cream",
        "1/2 cup sugar",
        "1 tsp vanilla extract",
        "Cocoa powder for topping"
    ),
    instructions = "Dip ladyfingers in espresso and layer with mascarpone mixture. Repeat layers and dust with cocoa powder. Refrigerate for at least 4 hours before serving."
)
    )
}