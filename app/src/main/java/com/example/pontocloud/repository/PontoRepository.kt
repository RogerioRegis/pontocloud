package com.example.pontocloud.repository

import com.example.pontocloud.database.PontoDao
import com.example.pontocloud.model.PontoRecord
import kotlinx.coroutines.flow.Flow

class PontoRepository(private val pontoDao: PontoDao) {
    val allRecords: Flow<List<PontoRecord>> = pontoDao.getAllRecords()

    suspend fun insert(record: PontoRecord) {
        pontoDao.insert(record)
    }

    suspend fun syncRecords() {
        val unsynced = pontoDao.getUnsyncedRecords()
        unsynced.forEach {
            // Simulate sync
            val syncedRecord = it.copy(isSynced = true)
            pontoDao.update(syncedRecord)
        }
    }
}
