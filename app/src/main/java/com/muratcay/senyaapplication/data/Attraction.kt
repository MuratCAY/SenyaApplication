package com.muratcay.senyaapplication.data
import android.net.Uri
import com.google.gson.annotations.SerializedName

data class Attraction(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("facts")
    val facts: List<String> = listOf(),
    @SerializedName("id")
    val id: String = "",
    @SerializedName("image_urls")
    val imageUrls: List<String> = listOf(),
    @SerializedName("location")
    val location: Location = Location(),
    @SerializedName("months")
    val months: String = "",
    @SerializedName("title")
    val title: String = ""
){
    data class Location(
        @SerializedName("latitude")
        val latitude: String = "",
        @SerializedName("longitude")
        val longitude: String = ""
    )
}