package com.example.imposter.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val word: String,
    val wordEn: String = "",
    val category: String,
    val difficulty: String,
    val isActive: Boolean = true
)
