package com.example.androidchallengecatapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.repository.CatRepository
import kotlinx.coroutines.launch


class CatViewModel(private val repo: CatRepository) : ViewModel() {

    private val _catList = MutableLiveData<List<Cat>>()
    val catList: LiveData<List<Cat>> get() = _catList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getRandomCats() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val cats = repo.getRandomCats()
                _catList.value = cats
            } catch (e: Exception) {
                // Manejo de error
            }
            _isLoading.value = false
        }
    }

    fun searchCatsByBreed(breedIds: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val cats = repo.searchCatsByBreed(breedIds)
                _catList.value = cats
            } catch (e: Exception) {
                // Manejo de error
            }
            _isLoading.value = false
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