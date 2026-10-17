package com.example.imposter.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM words WHERE isActive = 1")
    suspend fun getAllActiveWords(): List<WordEntity>

    @Query("SELECT * FROM words WHERE isActive = 1 AND category = :category")
    suspend fun getWordsByCategory(category: String): List<WordEntity>

    @Query("SELECT * FROM words WHERE isActive = 1 AND difficulty = :difficulty")
    suspend fun getWordsByDifficulty(difficulty: String): List<WordEntity>

    @Query("SELECT * FROM words WHERE isActive = 1 AND category = :category AND difficulty = :difficulty")
    suspend fun getWordsByCategoryAndDifficulty(category: String, difficulty: String): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordEntity>)

    @Query("SELECT COUNT(*) FROM words")
    suspend fun getWordCount(): Int
}
