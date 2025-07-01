package com.example.marketplace.data.repository

import com.example.marketplace.data.remote.Fail
import com.example.marketplace.domain.util.Resource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Response

fun <R> returnErrorResource(e: Exception): Resource.Error<R> {
    e.printStackTrace()
    return Resource.Error(
        message = e.message ?: "An unknown error occurred"
    )
}

fun <T, R> returnErrorResource(response: Response<T>): Resource.Error<R> {
    return if(response.errorBody() == null) {
        Resource.Error(
            message = "Unresolved Error"
        )
    } else {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Fail> = moshi.adapter<Fail>(Fail::class.java)

        val fail = adapter.fromJson(response.errorBody()!!.string())
        Resource.Error(
            message = fail?.detail?.getOrNull(0)?.msg ?: "Unresolved Error"
        )
    }
}