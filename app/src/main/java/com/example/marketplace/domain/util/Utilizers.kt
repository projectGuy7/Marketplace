package com.example.marketplace.domain.util

import com.example.marketplace.domain.marketplace.Item
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun String.toPart(): RequestBody {
    return this.toRequestBody("text/plain".toMediaTypeOrNull())
}

fun createPartMapFromItem(item: Item): MutableMap<String, RequestBody> {
    val map = mutableMapOf<String, RequestBody>()
    map["name"] = item.name.toPart()
    map["price"] = item.price.toString().toPart()
    map["quantity"] = item.quantity.toString().toPart()
    return map
}