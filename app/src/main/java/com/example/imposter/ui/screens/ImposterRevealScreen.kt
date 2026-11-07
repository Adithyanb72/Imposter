package com.example.imposter.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imposter.R
import com.example.imposter.model.Player
import com.example.imposter.ui.theme.AppBackground
import com.example.imposter.ui.theme.AppPrimary
import com.example.imposter.ui.theme.AppSecondaryText
import com.example.imposter.ui.theme.AppSurface

@Composable
fun ImposterRevealScreen(
    secretWord: String,
    imposterPlayer: Player?,
    onStartAgain: () -> Unit,
    onAddPlayers: () -> Unit,
    onNewGame: () -> Unit
) {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(AppBackground, Color(0xFF121212), Color(0xFF1A1A1A))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "IMPOSTER",
            color = AppPrimary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Secret Word Card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = AppSurface),
            border = BorderStroke(1.dp, AppPrimary.copy(alpha = 0.5f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Secret Word",
                    color = AppSecondaryText,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = secretWord,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Imposter Card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF4A2B2B)),
            border = BorderStroke(1.dp, Color(0xFFFF5252)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "The Imposter Was",
                    color = Color(0xFFFF8A8A),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = imposterPlayer?.name?.uppercase() ?: "UNKNOWN",
                    color = AppPrimary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pos),
                contentDescription = "Crying Cat",
                modifier = Modifier.size(240.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Buttons
        Button(
            onClick = onStartAgain,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppPrimary, contentColor = AppBackground)
        ) {
            Text(text = "CONTINUE (SAME PLAYERS)", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onAddPlayers,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppSurface, contentColor = Color.White),
            border = BorderStroke(1.dp, AppPrimary.copy(alpha = 0.5f))
        ) {
            Text(text = "ADD & EDIT PLAYERS", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onNewGame,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFF333333))
        ) {
            Text(text = "NEW GAME", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}
