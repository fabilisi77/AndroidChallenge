package com.example.androidchallengecatapi.repository

import com.example.androidchallengecatapi.data.model.Cat

interface CatRepository {
    suspend fun getRandomCats(): List<Cat>
    suspend fun searchCatsByBreed(breedIds: String): List<Cat>
}