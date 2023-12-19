package com.example.mihu.data.remote.response.user

data class UserResponse(
    val error: Boolean,
    val message: String,
    val data: UserDataDetails
)

data class UserDataDetails(
    val userId: String,
    val username: String,
    val email: String,
    val role: String
)