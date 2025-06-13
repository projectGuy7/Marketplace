package com.example.marketplace.domain.repository

import com.example.marketplace.domain.marketplace.Item
import com.example.marketplace.domain.marketplace.Order
import com.example.marketplace.domain.marketplace.User
import com.example.marketplace.domain.util.Resource
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart

interface MarketplaceRepository {

    suspend fun getMyUser(): Resource<User>

    suspend fun getUserById(userId: Int): Resource<User>

    suspend fun createUser(user: String): Resource<User>

    suspend fun deleteUserById(userId: Int): Resource<String>


    suspend fun getMyOrders(): Resource<List<Order>>

    suspend fun createOrder(order: Order): Resource<String>

    suspend fun deleteOrderById(orderId: Int): Resource<String>


    suspend fun getItems(): Resource<List<Item>>

    suspend fun getItemsByName(itemName: String): Resource<List<Item>>

    suspend fun createItem(item: Item): Resource<Item>

    suspend fun deleteItemById(itemId: Int): Resource<String>

}