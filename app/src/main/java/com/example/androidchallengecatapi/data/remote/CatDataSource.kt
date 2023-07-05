package com.example.androidchallengecatapi.data.remote

import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.repository.WebService


class CatDataSource(private val webService: WebService) {

    suspend fun getCatById(): List<Cat> = webService.getCatById()

    suspend fun searchByBreed(): List<Cat> = webService.searchByBreed()

}
