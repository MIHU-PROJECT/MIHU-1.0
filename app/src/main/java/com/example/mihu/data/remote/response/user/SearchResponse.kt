package com.example.mihu.data.remote.response.user

data class SearchResponse(
    val error: Boolean,
    val message: String,
    val data: Predictions
)

data class Predictions(
    val predictions: SearchItems
)

data class SearchItems(
    val _id: String,
    val name: String,
    val __v: Int,
    val categoriesImage: String,
    val description: String
)
