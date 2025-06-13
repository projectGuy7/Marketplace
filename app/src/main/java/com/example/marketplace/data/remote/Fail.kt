package com.example.marketplace.data.remote

data class Fail(
    val detail: List<Detail>
)

data class Detail(
    val loc: List<String>,
    val msg: String,
    val type: String
)