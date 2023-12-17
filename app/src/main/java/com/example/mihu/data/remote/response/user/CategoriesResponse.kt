package com.example.mihu.data.remote.response.user

data class CategoriesResponse(
    val error: Boolean,
    val message: String,
    val categories: List<Categories>,
)

data class Categories(
    val _id: String,
    val name: String,
    val __v: Int,
    val description: String,
    val categoriesImage: String
)
