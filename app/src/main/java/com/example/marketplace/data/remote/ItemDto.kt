package com.example.marketplace.data.remote

import com.squareup.moshi.Json

data class ItemDto(
    val name: String,
    val price: Double,
    val quantity: Int,
    @field:Json(name = "image_url")
    val imageUrl: String,
    @field:Json(name = "item_id")
    val itemId: Int
)
