package com.example.androidchallengecatapi.repository

import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.data.remote.CatDataSource


class CatRepositoryImpl(private val dataSource: CatDataSource) : CatRepository {
    override suspend fun getRandomCats(): List<Cat> {
        return dataSource.getRandomCats()
    }

    override suspend fun searchCatsByBreed(breedIds: String): List<Cat> {
        return dataSource.searchCatsByBreed(breedIds)
    }
}