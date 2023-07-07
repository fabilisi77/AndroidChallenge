package com.example.androidchallengecatapi.data.remote

import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.repository.WebService


class CatDataSource(private val webService: WebService) {
    suspend fun getRandomCats(): List<Cat> {
        val catResponses = webService.getRandomCats()
        return catResponses.map { catResponse ->
            Cat(catResponse.id, catResponse.url)
        }
    }

    suspend fun searchCatsByBreed(breedIds: String): List<Cat> {
        val catResponses = webService.searchCatsByBreed(breedIds)
        return catResponses.map { catResponse ->
            Cat(catResponse.id, catResponse.url)
        }
    }
}
