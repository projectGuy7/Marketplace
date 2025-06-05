package com.example.marketplace.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketplaceApi {

    @GET("/users/me")
    fun getMyUserDto(): UserDto

    @GET("/users/me/orders/")
    fun getMyOrderDtoList(): List<OrderDto>

    @GET("/users/")
    fun getUserDtoList(): List<UserDto>

    @GET("/users/{user_id}")
    fun getUserDtoById(
        @Path("user_id") userId: Int
    ): UserDto

    @GET("/items/")
    fun getItemDtoList(): List<ItemDto>

    @GET("/items/")
    fun getItemDtoListByName(
        @Query("name") itemName: String
    ): List<ItemDto>

    @GET("/items/{item_id}")
    fun getItemDtoById(
        @Path("item_id") itemId: Int
    ): ItemDto


}