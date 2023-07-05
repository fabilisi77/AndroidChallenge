package com.example.androidchallengecatapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatViewModel(private val repo: CatRepository) : ViewModel() {

    private val _catList = MutableLiveData<List<Cat>>()
    val catList: LiveData<List<Cat>> get() = _catList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getRandomCats() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val cats = withContext(Dispatchers.IO) {
                    repo.getCatById()
                }
                _catList.value = cats
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            _isLoading.value = false
        }
    }

    fun searchCatsByBreed(breedIds: String?) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val cats = withContext(Dispatchers.IO) {
                    repo.searchByBreed()
                }
                _catList.value = cats
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            _isLoading.value = false
        }
    }
}

class CatViewModelFactory(private val repo: CatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CatRepository::class.java).newInstance(repo)
    }
}


