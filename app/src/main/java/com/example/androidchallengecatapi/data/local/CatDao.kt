package com.example.androidchallengecatapi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidchallengecatapi.data.model.CatEntity

@Dao
interface CatDao {
    @Query("SELECT * FROM catentity")
    suspend fun getAllCats(): List<CatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCat(cat: CatEntity)
}