package com.example.marketplace.domain.marketplace

import java.time.LocalDate

data class Order(
    val address: String,
    val orderId: Int,
    val status: String,
    val orderDate: String,
    val items: List<Item>
)
