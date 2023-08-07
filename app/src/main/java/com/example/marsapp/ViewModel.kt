package com.example.marsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsapp.Model.MarsApiClass
import com.example.marsapp.Model.RetrofitClient
import kotlinx.coroutines.launch


class MarsViewModel : ViewModel() {


    private val apiService = RetrofitClient.retrofitInstance()

    fun getMars(): LiveData<List<MarsApiClass>> {
        val marsLiveData = MutableLiveData<List<MarsApiClass>>()

        viewModelScope.launch {
            try {
                val mars = apiService.fecthMarsList()
                marsLiveData.value = mars
            } catch (e: Exception) {
                // Manejar el error aquí
            }
        }

        return marsLiveData
    }
}