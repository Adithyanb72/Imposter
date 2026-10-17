package com.example.imposter.ui.screens

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
        colors = listOf(Color(0xFF171717), Color(0xFF0F0F0F))
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
                color = Color(0xFFF25623),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Pass the phone to:",
                color = Color.LightGray,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF222222)),
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
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(64.dp))

            Button(
                onClick = { showCardState = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF25623))
            ) {
                Text(text = "TAP TO REVEAL", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        } else {
            Text(
                text = "Player: ${player.name}",
                color = Color.LightGray,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isRevealed && player.isImposter) Color(0xFFD32F2F) else Color(0xFF222222)
                ),
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
                                    color = Color.White,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Try to blend in and guess the secret word!",
                                    color = Color.LightGray,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            } else {
                                Text(
                                    text = "YOUR SECRET WORD",
                                    color = Color(0xFFF25623),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = player.word ?: "",
                                    color = Color.White,
                                    fontSize = 48.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "👆",
                                fontSize = 48.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "HOLD TO REVEAL",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Remember your role. Do not show others.",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onNextPlayerClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF25623))
            ) {
                Text(text = "HIDE & NEXT PLAYER", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}