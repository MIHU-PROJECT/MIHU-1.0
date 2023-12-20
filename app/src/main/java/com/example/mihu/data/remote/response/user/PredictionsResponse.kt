package com.example.mihu.data.remote.response.user

data class PredictionsResponse(
    val error: Boolean,
    val message: String,
    val data: PredictionData
)

data class PredictionData(
    val predictions: List<Prediction>
)

data class Prediction(
    val _id: String,
    val name: String,
    val categoriesImage: String,
    val description: String
)
