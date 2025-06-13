package com.example.marketplace.data.remote

import com.squareup.moshi.Json

data class UserDto(
    val name: String,
    val email: String,
    val password: String,
    @field:Json(name = "user_id")
    val userId: Int
)
