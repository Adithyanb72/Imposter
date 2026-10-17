package com.example.imposter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayerCountScreen(
    onContinueClick: (Int) -> Unit
) {
    var playerCount by remember { mutableIntStateOf(5) }

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
        Text(
            text = "How many players?",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "എത്ര പേർ കളിക്കുന്നു?",
            color = Color(0xFFFFD700),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Counter Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Minus Button
            FilledIconButton(
                onClick = { if (playerCount > 4) playerCount-- },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color(0xFFF25623),
                    disabledContainerColor = Color.Gray
                ),
                enabled = playerCount > 4,
                modifier = Modifier.size(64.dp)
            ) {
                Text(text = "-", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }

            // Number Display
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color(0xFF222222)),
                modifier = Modifier.size(100.dp)
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = playerCount.toString(),
                        color = Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Plus Button
            FilledIconButton(
                onClick = { if (playerCount < 15) playerCount++ },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color(0xFFF25623),
                    disabledContainerColor = Color.Gray
                ),
                enabled = playerCount < 15,
                modifier = Modifier.size(64.dp)
            ) {
                Text(text = "+", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = { onContinueClick(playerCount) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF25623),
                contentColor = Color.White
            )
        ) {
            Text(text = "CONTINUE", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}