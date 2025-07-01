package com.example.marketplace.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class LoginInterceptor(private val accessToken: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", accessToken)
            .build()

        return chain.proceed(request)
    }

}