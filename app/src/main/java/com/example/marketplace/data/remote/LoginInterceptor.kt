package com.example.marketplace.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class LoginInterceptor(
    private val accessToken: String,
    private val refreshToken: String
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", accessToken)
            .addHeader("Second shit", refreshToken)
            .build()

        return chain.proceed(request)
    }

}