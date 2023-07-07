package com.example.androidchallengecatapi.data.model

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)

data class Cat(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)
