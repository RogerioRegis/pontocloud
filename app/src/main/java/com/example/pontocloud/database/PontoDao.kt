package com.example.pontocloud.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pontocloud.model.PontoRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface PontoDao {
    @Insert
    suspend fun insert(record: PontoRecord)

    @Query("SELECT * FROM ponto_records ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<PontoRecord>>

    @Query("SELECT * FROM ponto_records WHERE isSynced = 0")
    suspend fun getUnsyncedRecords(): List<PontoRecord>

    @Update
    suspend fun update(record: PontoRecord)
}
