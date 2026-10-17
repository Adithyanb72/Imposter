package com.example.imposter.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imposter.model.Player
import com.example.imposter.ui.theme.AppBackground
import com.example.imposter.ui.theme.AppPrimary
import com.example.imposter.ui.theme.AppSecondaryText
import com.example.imposter.ui.theme.AppSurface

@Composable
fun RevealScreen(
    player: Player,
    playerNumber: Int,
    totalPlayers: Int,
    onNextPlayerClick: () -> Unit
) {
    var isRevealed by remember(player.id) { mutableStateOf(false) }
    var showCardState by remember(player.id) { mutableStateOf(false) }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(AppBackground, Color(0xFF1F1F1F))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!showCardState) {
            Text(
                text = "🔒 SECRET CARD",
                color = AppPrimary,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Pass the phone to:",
                color = AppSecondaryText,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = AppSurface),
                border = BorderStroke(1.dp, Color(0x33A2FF00)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = player.name,
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Player $playerNumber of $totalPlayers",
                color = AppSecondaryText,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(56.dp))

            Button(
                onClick = { showCardState = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppPrimary, contentColor = AppBackground)
            ) {
                Text(text = "TAP TO REVEAL", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        } else {
            Text(
                text = "Player: ${player.name}",
                color = AppSecondaryText,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isRevealed && player.isImposter) Color(0xFFFF5252).copy(alpha = 0.2f) else AppSurface
                ),
                border = BorderStroke(1.dp, if (isRevealed && player.isImposter) Color(0xFFFF5252) else Color(0x33A2FF00)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                isRevealed = true
                                tryAwaitRelease()
                                isRevealed = false
                            }
                        )
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    if (isRevealed) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (player.isImposter) {
                                Text(
                                    text = "🚨 YOU ARE THE IMPOSTER 🚨",
                                    color = Color(0xFFFF5252),
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Try to blend in and guess the secret word!",
                                    color = AppSecondaryText,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center
                                )
                            } else {
                                Text(
                                    text = "YOUR SECRET WORD",
                                    color = AppPrimary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = player.word ?: "",
                                    color = Color.White,
                                    fontSize = 42.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "👆",
                                fontSize = 42.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "HOLD TO REVEAL",
                                color = AppPrimary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Remember your role. Do not show others.",
                color = AppSecondaryText,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onNextPlayerClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppPrimary, contentColor = AppBackground)
            ) {
                Text(text = "HIDE & NEXT PLAYER", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
