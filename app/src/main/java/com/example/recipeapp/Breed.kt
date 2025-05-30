package com.example.recipeapp

data class Breed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val reference_image_id: String?,
    val image: ImageData? = null
)

data class ImageData(
    val url: String
)
