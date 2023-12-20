package com.example.mihu.data.remote.response.user

import com.example.mihu.data.remote.response.worker.Order
import java.util.Date

data class CompleteJobResponse(

    val data: Dataz,

    val error: Boolean,

    val message: String
)

data class OrderX(

    val jobId: String,
    val workerId: String,
    val isCompleted: Boolean,
    val _id: String,
    val createdAt: Date,
    val updatedAt: Date,
    val __v: Int
)

data class Dataz(

    val order: Order
)
