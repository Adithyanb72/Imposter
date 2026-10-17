package com.example.imposter.model

enum class GamePhase {
    SETUP,
    REVEAL,
    DISCUSSION,
    VOTING,
    VOTE_RESULT,
    IMPOSTER_REVEAL,
    IMPOSTER_GUESS,
    RESULT
}

data class Player(
    val id: Int,
    val name: String,
    val isImposter: Boolean = false,
    val word: String? = null
)

data class GameState(
    val players: List<Player> = emptyList(),
    val currentPlayerIndex: Int = 0,
    val secretWord: String = "",
    val imposterPlayerId: Int = -1,
    val currentPhase: GamePhase = GamePhase.SETUP,
    val votes: Map<Int, Int> = emptyMap(), // voterId to votedPlayerId
    val winner: String? = null,
    val originalPlayerNames: List<String> = emptyList()
)
