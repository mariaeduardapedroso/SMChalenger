package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController

@Composable
fun RecipeApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MovieScreen.route) {
        composable(route = Screen.MovieScreen.route) {
            MovieScreen()
        }
    }
}
