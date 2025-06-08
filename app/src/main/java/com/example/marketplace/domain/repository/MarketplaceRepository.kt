package com.example.marketplace.domain.repository

import com.example.marketplace.domain.marketplace.User
import com.example.marketplace.domain.util.Resource

interface MarketplaceRepository {

    suspend fun getMyUser(): Resource<User>

    suspend fun getUserById(userId: Int): Resource<User>

    suspend fun createUser(user: String): Resource<String>

    suspend fun deleteUserById(userId: Int): Resource<String>


}