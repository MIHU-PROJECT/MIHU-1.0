package com.example.mihu.data.remote.response.user

import com.example.mihu.data.remote.response.worker.Data

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val data: Data,
)

data class Data(
    val userId: String,
    val username: String,
    val email: String,
    val accessToken: String,
    val role: String
)

