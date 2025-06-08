package com.example.marketplace.domain.marketplace

data class Item(
    val name: String,
    val price: Int,
    val quantity: Int,
    val imageUrl: String,
    val itemId: Int
)
