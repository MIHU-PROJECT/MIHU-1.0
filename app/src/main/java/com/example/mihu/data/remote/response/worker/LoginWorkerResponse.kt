package com.example.mihu.data.remote.response.worker

import com.example.mihu.data.remote.response.user.Data

data class LoginWorkerResponse(
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

