package com.example.marketplace.data.mappers

import com.example.marketplace.data.remote.ItemDto
import com.example.marketplace.data.remote.OrderDto
import com.example.marketplace.domain.marketplace.Item
import com.example.marketplace.domain.marketplace.User
import com.example.marketplace.data.remote.UserDto
import com.example.marketplace.domain.marketplace.Order

fun ItemDto.toItem(): Item {
    return Item(
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        imageUri = this.imageUri,
        itemId = this.itemId
    )
}

fun UserDto.toUser(): User {
    return User(
        name = this.name,
        email = this.email,
        userId = this.userId
    )
}

// TODO("convert DTO's address to Address, orderDate to LocalDateTime")
fun OrderDto.toOrder(): Order {
    return Order(
        address = this.address,
        orderId = this.orderId,
        status = this.status,
        orderDate = this.orderDate,
        items = this.items.map { it.toItem() }
    )
}

fun Order.toOrderDto(): OrderDto {
    return OrderDto(
        address = this.address,
        orderId = this.orderId,
        status = this.status,
        orderDate = this.orderDate,
        items = this.items.map { it.toItemDto() }
    )
}

fun Item.toItemDto(): ItemDto {
    return ItemDto(
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        imageUri = this.imageUri,
        itemId = this.itemId
    )
}