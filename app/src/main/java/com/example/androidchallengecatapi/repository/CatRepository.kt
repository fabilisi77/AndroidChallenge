package com.example.androidchallengecatapi.repository

import com.example.androidchallengecatapi.data.model.Cat

interface CatRepository {
    suspend fun getCatById(): List<Cat>
    suspend fun searchByBreed(): List<Cat>
}