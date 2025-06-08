package com.example.marketplace.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.DELETE

interface MarketplaceApi {

    // USERS

    @GET("/users/me")
    fun getMyUserDto(): UserDto

    @GET("/users/")
    fun getUserDtoList(): List<UserDto>

    @GET("/users/{user_id}")
    fun getUserDtoById(
        @Path("user_id") userId: Int
    ): UserDto

    @POST("/users/")
    fun createUser(
        @Body user: String
    ): Response<String>

    @DELETE("/users/{user_id}")
    fun deleteUserById(
       @Path("user_id") userId: Int
    ): Response<String>

    // ORDERS

    @GET("/users/me/orders/")
    fun getMyOrderDtoList(): List<OrderDto>

    @POST("/users/me/orders/")
    fun createOrder(
        @Body order: OrderDto
    ): Response<String>

    @DELETE("/orders/{order_id}")
    fun deleteOrderById(
        @Path("order_id") orderId: Int
    ): Response<String>

    // ITEMS

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

    @Multipart
    @POST("/items/")
    fun createItem(
        @PartMap partMap: MutableMap<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): Response<ItemDto>

    @DELETE("/items/{item_id}")
    fun deleteItemById(
        @Path("item_id") itemId: Int
    ): Response<String>
}