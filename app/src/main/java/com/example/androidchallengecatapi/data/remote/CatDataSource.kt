package com.example.androidchallengecatapi.data.remote

import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.repository.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CatDataSource(private val webService: WebService) {
    suspend fun getRandomCats(): List<Cat> {
      return  withContext(Dispatchers.IO) {

            val catResponses = webService.getRandomCats()
             catResponses.map { catResponse ->
                Cat(catResponse.id, catResponse.url)
            }
        }
    }

    suspend fun searchCatsByBreed(breedIds: String): List<Cat> {
       return withContext(Dispatchers.IO) {
            val catResponses = webService.searchCatsByBreed(breedIds)
             catResponses.map { catResponse ->
                Cat(catResponse.id, catResponse.url)
            }
        }
    }
}
