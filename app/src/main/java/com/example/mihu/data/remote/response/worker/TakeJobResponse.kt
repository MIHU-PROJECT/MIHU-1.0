package com.example.mihu.data.remote.response.worker

import java.util.Date

data class TakeJobResponse(

    val data: Datax,

    val error: Boolean,

    val message: String
)

data class Order(

    val jobId: String,
    val workerId: String,
    val isCompleted: Boolean,
    val _id: String,
    val createdAt: Date,
    val updatedAt: Date,
    val __v: Int
)

data class Datax(

    val order: Order
)
