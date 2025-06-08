package com.example.marketplace.domain.marketplace

data class Address(
    val country: String,
    val state: String,
    val city: String,
    val postalCode: Int,
    val street: String,
    val isApartment: Boolean,
    val apartmentName: String? = null,
    val apartmentNumber: Int? = null,
    val houseName: String? = null
) {
    override fun toString(): String {
        if(isApartment) {
            return "$country, $state, $city, Postal Code: $postalCode, $street, $apartmentName, $apartmentNumber"
        } else {
            return "$country, $state, $city, Postal Code: $postalCode, $street, $houseName"
        }
    }
}
