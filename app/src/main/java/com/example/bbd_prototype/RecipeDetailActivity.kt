package com.example.bbd_prototype

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val ingredients = intent.getStringArrayListExtra("ingredients")
        val instructions = intent.getStringExtra("instructions")

        findViewById<TextView>(R.id.detailTitle).text = title
        findViewById<TextView>(R.id.detailDescription).text = description
        findViewById<TextView>(R.id.detailIngredients).text =
            ingredients?.joinToString("\n") { "• $it" } ?: ""

        findViewById<TextView>(R.id.detailInstructions).text = instructions
    }
}