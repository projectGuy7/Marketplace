package com.example.marketplace.domain.marketplace

data class User(
    val name: String,
    val email: String,
    val password: String,
    val userId: Int = -1
)
