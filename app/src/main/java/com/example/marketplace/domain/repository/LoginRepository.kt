package com.example.marketplace.domain.repository

import com.example.marketplace.domain.marketplace.Token
import com.example.marketplace.domain.marketplace.User
import com.example.marketplace.domain.util.Resource

interface LoginRepository {

    suspend fun login(username: String, password: String): Resource<Token>

    suspend fun refreshToken(refreshToken: String): Resource<Token>

    suspend fun register(user: User): Resource<String>

    suspend fun verifyEmail(email: String, code: String): Resource<Token>

}