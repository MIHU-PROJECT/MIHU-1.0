package com.example.mihu.data.remote.response.worker

data class HistoryWorkerResponse(

    val data: Datay,
    val error: Boolean,
    val message: String
)

data class Datay(
    val orders: List<OrdersItem>
)

data class OrdersItem(

    val createdAt: String,
    val price: Int,
    val name: String,
    val recruiter: String,
    val description: String,
    val category: String,
    val status: String,
    val updatedAt: String
)
