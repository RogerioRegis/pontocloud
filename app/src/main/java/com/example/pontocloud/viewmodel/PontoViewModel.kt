package com.example.pontocloud.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.pontocloud.database.AppDatabase
import com.example.pontocloud.model.PontoRecord
import com.example.pontocloud.repository.PontoRepository
import kotlinx.coroutines.launch

class PontoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PontoRepository
    val allRecords: LiveData<List<PontoRecord>>

    init {
        val pontoDao = AppDatabase.getDatabase(application).pontoDao()
        repository = PontoRepository(pontoDao)
        allRecords = repository.allRecords.asLiveData()
    }

    fun registerPonto(latitude: Double?, longitude: Double?) = viewModelScope.launch {
        val record = PontoRecord(
            timestamp = System.currentTimeMillis(),
            latitude = latitude,
            longitude = longitude,
            isSynced = false // Initial status
        )
        repository.insert(record)
    }

    fun syncRecords() = viewModelScope.launch {
        repository.syncRecords()
    }
}
