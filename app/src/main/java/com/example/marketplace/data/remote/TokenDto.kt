package com.example.marketplace.data.remote

import com.squareup.moshi.Json

data class TokenDto(
    @field:Json(name = "access_token")
    val accessToken: String,
    @field:Json(name = "token_type")
    val tokenType: String,
    @field:Json(name = "refresh_token")
    val refreshToken: String
)