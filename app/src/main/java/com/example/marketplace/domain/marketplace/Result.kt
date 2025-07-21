package com.example.marketplace.domain.marketplace

data class Success(
    val message: String
)

data class Fail(
    val detail: String
)