package com.example.mihu.data.remote.response.worker

data class JobWorkerResponse(
    val error: Boolean,
    val message: String,
    val data: JobData
)

data class JobData(
    val jobs: List<Job>
)

data class Job(
    val _id: String,
    val name: String,
    val description: String,
    val categoryId: String,
    val price: Int,
    val createdBy: String,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)