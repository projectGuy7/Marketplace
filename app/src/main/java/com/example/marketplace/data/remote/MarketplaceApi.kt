package com.example.marketplace.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
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
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface MarketplaceApi {

    // Authorization

    @FormUrlEncoded
    @POST("/token")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<TokenDto>

    @POST("/token/refresh")
    suspend fun refreshToken(
        @Body refreshToken: Map<String, String>
    ): Response<TokenDto>

    @POST("/register")
    suspend fun register(
        @Body user: UserDto
    ): Response<String>

    @POST("/verify_email")
    suspend fun verifyEmail(
        @Body emailVerification: Map<String, String>
    ): Response<TokenDto>

    // USERS

    @GET("/users/me")
    suspend fun getMyUserDto(): UserDto

    @GET("/users/")
    suspend fun getUserDtoList(): List<UserDto>

    @GET("/users/{user_id}")
    suspend fun getUserDtoById(
        @Path("user_id") userId: Int
    ): UserDto

    @POST("/users/")
    suspend fun createUser(
        @Body user: UserDto
    ): Response<UserDto>

    @DELETE("/users/{user_id}")
    suspend fun deleteUserById(
       @Path("user_id") userId: Int
    ): Response<String>

    // ORDERS

    @GET("/users/me/orders/")
    suspend fun getMyOrderDtoList(): List<OrderDto>

    @POST("/users/me/orders/")
    suspend fun createOrder(
        @Body order: OrderDto
    ): Response<String>

    @DELETE("/orders/{order_id}")
    suspend fun deleteOrderById(
        @Path("order_id") orderId: Int
    ): Response<String>

    // ITEMS

    @GET("/items/")
    suspend fun getItemDtoList(): List<ItemDto>

    @GET("/items/")
    suspend fun getItemDtoListByName(
        @Query("name") itemName: String
    ): List<ItemDto>

    @GET("/items/{item_id}")
    suspend fun getItemDtoById(
        @Path("item_id") itemId: Int
    ): ItemDto

    @Multipart
    @POST("/items/")
    suspend fun createItem(
        @PartMap partMap: MutableMap<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): Response<ItemDto>

    @DELETE("/items/{item_id}")
    suspend fun deleteItemById(
        @Path("item_id") itemId: Int
    ): Response<String>
}