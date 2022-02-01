package com.muratcay.senyaapplication.data

import com.google.gson.annotations.SerializedName

data class Attraction(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("facts")
    val facts: List<String> = listOf(),
    @SerializedName("id")
    val id: String = "",
    @SerializedName("location")
    val location: Location = Location(),
    @SerializedName("months")
    val months: String = "",
    @SerializedName("title")
    val title: String = "",
    val url_image: List<String> = listOf(),
    ) {
    data class Location(
        @SerializedName("latitude")
        val latitude: String = "",
        @SerializedName("longitude")
        val longitude: String = ""
    )
}