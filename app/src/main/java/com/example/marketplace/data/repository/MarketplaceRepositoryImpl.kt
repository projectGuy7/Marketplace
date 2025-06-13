package com.example.marketplace.data.repository

import com.example.marketplace.data.mappers.toItem
import com.example.marketplace.data.mappers.toOrder
import com.example.marketplace.data.mappers.toOrderDto
import com.example.marketplace.data.mappers.toUser
import com.example.marketplace.data.remote.Fail
import com.example.marketplace.data.remote.MarketplaceApi
import com.example.marketplace.data.remote.UserDto
import com.example.marketplace.domain.marketplace.Item
import com.example.marketplace.domain.marketplace.Order
import com.example.marketplace.domain.marketplace.User
import com.example.marketplace.domain.repository.MarketplaceRepository
import com.example.marketplace.domain.util.Resource
import retrofit2.Response
import java.io.File
import okhttp3.RequestBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import com.example.marketplace.domain.util.createPartMapFromItem
import com.squareup.moshi.JsonAdapter

class MarketplaceRepositoryImpl(
    private val api: MarketplaceApi,
    private val adapter: JsonAdapter<Fail>
): MarketplaceRepository {

    override suspend fun getMyUser(): Resource<User> {
        return try {
            Resource.Success(
                data = api.getMyUserDto().toUser()
            )
        } catch(e: Exception) {
            returnErrorResource(e)
        }
    }

    override suspend fun getUserById(userId: Int): Resource<User> {
        return try {
            Resource.Success(
                data = api.getUserDtoById(userId).toUser()
            )
        } catch(e: Exception) {
            returnErrorResource(e)
        }
    }

    override suspend fun createUser(user: String): Resource<User> {
        val response = api.createUser(user)

        return if(response.isSuccessful) {
            Resource.Success(
                data = response.body()!!.toUser()
            )
        } else {
            returnErrorResource(response)
        }
    }

    override suspend fun deleteUserById(userId: Int): Resource<String> {
        val response = api.deleteUserById(userId)

        return if(response.isSuccessful) {
            Resource.Success(
                data = "Successfully deleted user"
            )
        } else {
            returnErrorResource(response)
        }
    }

    override suspend fun getMyOrders(): Resource<List<Order>> {
        return try {
            Resource.Success(
                data = api.getMyOrderDtoList().map { it.toOrder() }
            )
        } catch(e: Exception) {
            returnErrorResource(e)
        }
    }

    override suspend fun createOrder(order: Order): Resource<String> {
        val response = api.createOrder(order.toOrderDto())

        return if(response.isSuccessful) {
            Resource.Success(
                data = "Successfully created order"
            )
        } else {
            returnErrorResource(response)
        }
    }

    override suspend fun deleteOrderById(orderId: Int): Resource<String> {
        val response = api.deleteOrderById(orderId)

        return if(response.isSuccessful) {
            Resource.Success(
                data = "Successfully deleted order"
            )
        } else {
            returnErrorResource(response)
        }
    }

    override suspend fun getItems(): Resource<List<Item>> {
        return try {
            Resource.Success(
                data = api.getItemDtoList().map { it.toItem() }
            )
        } catch(e: Exception) {
            returnErrorResource(e)
        }
    }

    override suspend fun getItemsByName(itemName: String): Resource<List<Item>> {
        return try {
            Resource.Success(
                data = api.getItemDtoListByName(itemName).map { it.toItem() }
            )
        } catch(e: Exception) {
            returnErrorResource(e)
        }
    }

    override suspend fun createItem(item: Item): Resource<Item> {
        val file = File(item.imageUri)
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("image", file.name, requestBody)

        val response = api.createItem(
            image = multipartBody,
            partMap = createPartMapFromItem(item)
        )
        return if(response.isSuccessful) {
            Resource.Success(
                data = response.body()!!.toItem()
            )
        } else {
            returnErrorResource(response)
        }
    }

    override suspend fun deleteItemById(itemId: Int): Resource<String> {
        val response = api.deleteItemById(itemId)

        return if(response.isSuccessful) {
            Resource.Success(
                data = "Successfully deleted item"
            )
        } else {
            returnErrorResource(response)
        }
    }

    private suspend fun <R> returnErrorResource(e: Exception): Resource.Error<R> {
        e.printStackTrace()
        return Resource.Error(
            message = e.message ?: "An unknown error occurred"
        )
    }

    private suspend fun <T, R> returnErrorResource(response: Response<T>): Resource.Error<R> {
        return if(response.errorBody() == null) {
            Resource.Error(
                message = "Unresolved Error"
            )
        } else {
            val fail = adapter.fromJson(response.errorBody()!!.string())
            Resource.Error(
                message = fail?.detail?.getOrNull(0)?.msg ?: "Unresolved Error"
            )
        }
    }

}