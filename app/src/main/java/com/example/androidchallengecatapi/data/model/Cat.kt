package com.example.androidchallengecatapi.data.model

import com.google.gson.annotations.SerializedName


data class Cat(
    @SerializedName("val") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("breeds") val breeds: List<Breed>
)

data class Breed(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

