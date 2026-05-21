package com.example.pontocloud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ponto_records")
data class PontoRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long,
    val latitude: Double?,
    val longitude: Double?,
    val isSynced: Boolean = false,
    val photoPath: String? = null
)
