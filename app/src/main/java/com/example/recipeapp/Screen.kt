package com.example.recipeapp

// Individual routes
// Type safety
sealed class Screen(val route:String) {
    object RecipeScreen:Screen("catscreen")
    object DetailScreen:Screen("detailscreen")

}