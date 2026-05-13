package com.example.bbd_prototype.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.bbd_prototype.screens.LoginScreen
import com.example.bbd_prototype.screens.MainScreen

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