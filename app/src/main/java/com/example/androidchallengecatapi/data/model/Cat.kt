package com.example.androidchallengecatapi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)

data class Cat(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)

@Entity
data class CatEntity(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @ColumnInfo (name = "url")
    @SerializedName("url") val url: String
)
