package com.example.imposter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.imposter.model.GamePhase
import com.example.imposter.ui.screens.DiscussionScreen
import com.example.imposter.ui.screens.HomeScreen
import com.example.imposter.ui.screens.HowToPlayScreen
import com.example.imposter.ui.screens.ImposterRevealScreen
import com.example.imposter.ui.screens.LandingScreen
import com.example.imposter.ui.screens.PlayerCountScreen
import com.example.imposter.ui.screens.PlayerNamesScreen
import com.example.imposter.ui.screens.RevealScreen
import com.example.imposter.ui.screens.SettingsScreen
import com.example.imposter.ui.theme.ImposterTheme
import com.example.imposter.viewmodel.GameViewModel
import com.example.imposter.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImposterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImposterApp()
                }
            }
        }
    }
}

@Composable
fun ImposterApp(
    viewModel: GameViewModel = viewModel(),
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val navController = rememberNavController()
    val gameState = viewModel.gameState

    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") {
            LandingScreen(
                onTimeout = {
                    navController.navigate("home") {
                        popUpTo("landing") { inclusive = true }
                    }
                }
            )
        }
        composable("home") {
            HomeScreen(
                onStartGameClick = { navController.navigate("player_count") },
                onHowToPlayClick = { navController.navigate("how_to_play") },
                onSettingsClick = { navController.navigate("settings") }
            )
        }
        composable("how_to_play") {
            HowToPlayScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("settings") {
            SettingsScreen(
                soundEnabled = settingsViewModel.soundEnabled,
                onSoundChanged = { settingsViewModel.soundEnabled = it },
                vibrationEnabled = settingsViewModel.vibrationEnabled,
                onVibrationChanged = { settingsViewModel.vibrationEnabled = it },
                timerEnabled = settingsViewModel.timerEnabled,
                onTimerChanged = { settingsViewModel.timerEnabled = it },
                badWordsEnabled = settingsViewModel.badWordsEnabled,
                onBadWordsChanged = { settingsViewModel.badWordsEnabled = it },
                difficulty = settingsViewModel.selectedDifficulty,
                onDifficultyChanged = { settingsViewModel.selectedDifficulty = it },
                category = settingsViewModel.selectedCategory,
                onCategoryChanged = { settingsViewModel.selectedCategory = it },
                language = settingsViewModel.selectedLanguage,
                onLanguageChanged = { settingsViewModel.selectedLanguage = it },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("player_count") {
            PlayerCountScreen(
                onContinueClick = { count ->
                    navController.navigate("player_names/$count")
                }
            )
        }
        composable(
            route = "player_names/{count}",
            arguments = listOf(navArgument("count") { type = NavType.IntType })
        ) { backStackEntry ->
            val count = backStackEntry.arguments?.getInt("count") ?: 5
            val initialNames = gameState.originalPlayerNames
            PlayerNamesScreen(
                playerCount = count,
                initialNames = initialNames,
                onStartGameClick = { playerNames ->
                    viewModel.initializeGame(playerNames)
                    navController.navigate("reveal") {
                        popUpTo("home")
                    }
                }
            )
        }
        composable("reveal") {
            if (gameState.players.isNotEmpty() && gameState.currentPlayerIndex < gameState.players.size) {
                val currentPlayer = gameState.players[gameState.currentPlayerIndex]
                RevealScreen(
                    player = currentPlayer,
                    playerNumber = gameState.currentPlayerIndex + 1,
                    totalPlayers = gameState.players.size,
                    onNextPlayerClick = {
                        viewModel.nextPlayerReveal()
                        if (viewModel.gameState.currentPhase == GamePhase.DISCUSSION) {
                            navController.navigate("discussion") {
                                popUpTo("reveal") { inclusive = true }
                            }
                        }
                    }
                )
            }
        }
        composable("discussion") {
            DiscussionScreen(
                players = gameState.players,
                onRevealImposterClick = {
                    viewModel.proceedToReveal()
                    navController.navigate("result") {
                        popUpTo("discussion") { inclusive = true }
                    }
                }
            )
        }
        composable("result") {
            val imposter = gameState.players.find { it.isImposter }
            ImposterRevealScreen(
                secretWord = gameState.secretWord,
                imposterPlayer = imposter,
                onStartAgain = {
                    viewModel.restartWithSamePlayers()
                    navController.navigate("reveal") {
                        popUpTo("result") { inclusive = true }
                    }
                },
                onAddPlayers = {
                    val count = gameState.originalPlayerNames.size.coerceAtLeast(5)
                    navController.navigate("player_names/$count")
                },
                onNewGame = {
                    navController.navigate("player_count") {
                        popUpTo("home") { inclusive = false }
                    }
                }
            )
        }
    }
}
