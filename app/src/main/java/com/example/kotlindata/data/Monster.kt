package com.example.kotlindata.data

import com.example.kotlindata.IMAGE_BASE_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Monster(
    @Json(name = "monsterName")
    val name: String,
    val imageFile: String,
    val caption: String,
    val description: String,
    val price: Double,
    val scariness: Int
)
{
    val imageUrl
    get() = "$IMAGE_BASE_URL/$imageFile.webp"

    val thumbnilUrl
    get() = "$IMAGE_BASE_URL/{$imageFile}_tn.webp"
}