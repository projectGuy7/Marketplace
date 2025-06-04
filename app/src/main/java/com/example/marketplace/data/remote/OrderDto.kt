package com.example.marketplace.data.remote

import com.squareup.moshi.Json

data class OrderDto(
    val address: String,
    @field:Json(name = "order_id")
    val orderId: Int,
    val status: String,
    @field:Json(name = "order_date")
    val orderDate: String,
    val items: List<ItemDto>
)
