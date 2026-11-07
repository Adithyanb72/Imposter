package com.example.imposter.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.imposter.data.local.AppDatabase
import com.example.imposter.data.repository.GameRepository
import com.example.imposter.model.GamePhase
import com.example.imposter.model.GameState
import com.example.imposter.model.Player
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GameRepository

    var gameState by mutableStateOf(GameState())
        private set

    init {
        val wordDao = AppDatabase.getDatabase(application).wordDao()
        repository = GameRepository(wordDao)
    }

    fun initializeGame(
        playerNames: List<String>,
        language: String = "Malayalam",
        includeBadWords: Boolean = false,
        category: String = "All",
        difficulty: String = "Mixed",
        onReady: () -> Unit = {}
    ) {
        viewModelScope.launch {
            val cat = if (category == "All") null else category
            val diff = if (difficulty == "Mixed") null else difficulty
            val randomWordEntity = repository.getRandomWord(category = cat, difficulty = diff, includeBadWords = includeBadWords)
            val secretWord = if (language == "English") {
                randomWordEntity.wordEn.ifEmpty { randomWordEntity.word.ifEmpty { "Mango" } }
            } else {
                randomWordEntity.word.ifEmpty { randomWordEntity.wordEn.ifEmpty { "മാങ്ങ" } }
            }

            val imposterIndex = playerNames.indices.random()
            val initialPlayers = playerNames.mapIndexed { index, name ->
                Player(
                    id = index + 1,
                    name = name,
                    isImposter = (index == imposterIndex),
                    word = if (index == imposterIndex) null else secretWord
                )
            }

            gameState = GameState(
                players = initialPlayers,
                currentPlayerIndex = 0,
                secretWord = secretWord,
                imposterPlayerId = initialPlayers[imposterIndex].id,
                currentPhase = GamePhase.REVEAL,
                originalPlayerNames = playerNames,
                gameSessionId = System.currentTimeMillis()
            )
            onReady()
        }
    }

    fun nextPlayerReveal() {
        val nextIndex = gameState.currentPlayerIndex + 1
        if (nextIndex < gameState.players.size) {
            gameState = gameState.copy(currentPlayerIndex = nextIndex)
        } else {
            gameState = gameState.copy(
                currentPlayerIndex = 0,
                currentPhase = GamePhase.DISCUSSION
            )
        }
    }

    fun proceedToReveal() {
        gameState = gameState.copy(currentPhase = GamePhase.IMPOSTER_REVEAL)
    }

    fun restartWithSamePlayers(
        language: String = "Malayalam",
        includeBadWords: Boolean = false,
        category: String = "All",
        difficulty: String = "Mixed",
        onReady: () -> Unit = {}
    ) {
        if (gameState.originalPlayerNames.isNotEmpty()) {
            initializeGame(gameState.originalPlayerNames, language, includeBadWords, category, difficulty, onReady)
        }
    }
}
