package com.example.marketplace.data.repository

import com.example.marketplace.data.mappers.toToken
import com.example.marketplace.data.mappers.toUserDto
import com.example.marketplace.data.remote.MarketplaceApi
import com.example.marketplace.di.ApiWithoutInterceptor
import com.example.marketplace.domain.marketplace.Token
import com.example.marketplace.domain.marketplace.User
import com.example.marketplace.domain.repository.LoginRepository
import com.example.marketplace.domain.util.Resource
import javax.inject.Inject
import javax.inject.Named

class LoginRepositoryImpl @Inject constructor(
    @ApiWithoutInterceptor
    private val api: MarketplaceApi
): LoginRepository {
    override suspend fun login(username: String, password: String): Resource<Token> {
        val response = api.login(username, password)

        return if(response.isSuccessful) {
            Resource.Success(
                data = response.body()!!.toToken()
            )
        } else {
            returnErrorResource(response)
        }
    }

    override suspend fun refreshToken(refreshToken: String): Resource<Token> {
        val response = api.refreshToken(mapOf(Pair("refresh_token", refreshToken)))

        return if(response.isSuccessful) {
            Resource.Success(
                data = response.body()!!.toToken()
            )
        } else {
            returnErrorResource(response)
        }
    }

    override suspend fun register(user: User): Resource<String> {
        val response = api.register(user.toUserDto())

        return if(response.isSuccessful) {
            Resource.Success(
                data = "Successfully deleted user"
            )
        } else {
            returnErrorResource(response)
        }
    }

    override suspend fun verifyEmail(email: String, code: String): Resource<Token> {
        val response = api.verifyEmail(mapOf(Pair("email", email), Pair("code", code)))

        return if (response.isSuccessful) {
            Resource.Success(
                data = response.body()!!.toToken()
            )
        } else {
            returnErrorResource(response)
        }
    }
}