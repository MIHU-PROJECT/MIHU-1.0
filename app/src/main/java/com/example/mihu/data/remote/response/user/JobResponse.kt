package com.example.mihu.data.remote.response.user

import java.util.Date


class JobResponse(
    val error: Boolean,
    val message: String,
    val job: Jobs,
)

data class Jobs(
    val name: String,
    val description: String,
    val categoryId: String,
    val price: Number,
    val createdBy: String,
    val isActive: Boolean,
    val _id: String,
    val createdAt: Date,
    val updatedAt: Date,
    val __v: Int
) 