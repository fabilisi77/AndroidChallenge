package com.example.androidchallengecatapi.repository

import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.data.remote.CatDataSource

class CatRepositoryImpl(private val dataSource: CatDataSource) : CatRepository {
    override suspend fun getCatById(): List<Cat> {
        return dataSource.getCatById()
    }

    override suspend fun searchByBreed(): List<Cat> {
        return dataSource.searchByBreed()
    }

}


