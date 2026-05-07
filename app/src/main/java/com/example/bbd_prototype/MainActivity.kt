package com.example.bbd_prototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val recipes = listOf(
        Recipe(
            1,
            "Neapolitan Pizza Dough",
            "Classic Pizza Dough meant for a wood fired oven",
            listOf(
                "Flour 950 g",
                "Chilled Water 545 g",
                "Dry Active Yeast 1.5 g",
                "Sea Salt 24 g"
            ),
            "Mix ingredients and knead until smooth."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecipeAdapter(recipes)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_saved -> {
                    // Show saved recipes
                    true
                }

                R.id.nav_explore -> {
                    // Later: switch to explore screen
                    true
                }

                else -> false
            }
        }
    }
}