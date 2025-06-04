package com.example.marketplace.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MarketplaceApi {

    @GET("/users/me")
    fun getMyUserDto(): UserDto

    @GET("/users/me/orders/")
    fun getMyOrderDtoList(): List<OrderDto>

    @GET("/users/")
    fun getUserDtoList(): List<UserDto>

    @GET("/users/")
    fun getUserDtoById(
        @Query("") userId: Int
    ): UserDto

    @GET("/items/")
    fun getItemDtoList(): List<ItemDto>

    @GET("/items/")
    fun getItemDtoListByName(
        @Query("name") itemName: String
    ): List<ItemDto>

    @GET("/items/")
    fun getItemDtoById(
        @Query("") itemId: Int
    ): ItemDto
}