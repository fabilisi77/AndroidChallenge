package com.example.androidchallengecatapi.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.androidchallengecatapi.core.Resource
import com.example.androidchallengecatapi.repository.CatRepository
import kotlinx.coroutines.Dispatchers

class CatViewModel(private val repo: CatRepository) : ViewModel() {

    fun fetchRandomCats() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val cats = repo.getRandomCats()
            emitSource(liveData { emit(Resource.Success(cats)) })
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchCatsByBreed(breedIds: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val cats = repo.searchCatsByBreed(breedIds)
            emitSource(liveData { emit(Resource.Success(cats)) })
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class CatViewModelFactory(private val repo: CatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
            return CatViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}