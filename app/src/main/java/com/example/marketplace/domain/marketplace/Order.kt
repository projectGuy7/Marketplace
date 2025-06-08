package com.example.marketplace.domain.marketplace

import java.time.LocalDate

data class Order(
    val address: Address,
    val orderId: Int,
    val status: String,
    val orderDate: LocalDate,
    val items: List<Item>
)
