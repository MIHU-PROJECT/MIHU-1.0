package com.example.mihu.data.remote.response.user

data class HistoryResponse(

    val error: Boolean,
    val message: String,
    val data: DataHistory
)

data class DataHistory(
    val orders: List<OrdersItem>
)

data class OrdersItem(

    val createdAt: String,
    val price: Int,
    val name: String,
    val description: String,
    val category: String,
    val status: String,
    val updatedAt: String
)
