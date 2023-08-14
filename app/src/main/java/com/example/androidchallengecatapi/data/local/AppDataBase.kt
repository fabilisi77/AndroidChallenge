package com.example.androidchallengecatapi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidchallengecatapi.data.model.CatEntity

@Database(entities = [CatEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun catDao(): CatDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getDatBase(cotext: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                cotext.applicationContext,
                AppDataBase::class.java,
                "cat_table"
            ).build()

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}