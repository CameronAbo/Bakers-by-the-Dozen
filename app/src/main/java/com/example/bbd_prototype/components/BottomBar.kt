package com.example.bbd_prototype.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

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