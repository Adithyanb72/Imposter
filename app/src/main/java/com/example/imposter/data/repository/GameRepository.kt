package com.example.imposter.data.repository

import com.example.imposter.data.local.WordDao
import com.example.imposter.data.local.WordEntity

class GameRepository(private val wordDao: WordDao) {
    suspend fun getRandomWord(category: String? = null, difficulty: String? = null): WordEntity? {
        val words = when {
            category != null && difficulty != null -> wordDao.getWordsByCategoryAndDifficulty(category, difficulty)
            category != null -> wordDao.getWordsByCategory(category)
            difficulty != null -> wordDao.getWordsByDifficulty(difficulty)
            else -> wordDao.getAllActiveWords()
        }
        if (words.isNotEmpty()) {
            return words.random()
        }
        // Fallback default words if DB is empty
        val fallbacks = listOf(
            WordEntity(word = "മാങ്ങ", category = "FOOD", difficulty = "EASY"),
            WordEntity(word = "ചക്ക", category = "FOOD", difficulty = "EASY"),
            WordEntity(word = "തേങ്ങ", category = "FOOD", difficulty = "EASY"),
            WordEntity(word = "പഴം", category = "FOOD", difficulty = "EASY"),
            WordEntity(word = "പുട്ട്", category = "FOOD", difficulty = "EASY")
        )
        return fallbacks.random()
    }
}
