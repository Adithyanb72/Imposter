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
import com.example.imposter.ui.theme.AppBackground
import com.example.imposter.ui.theme.AppPrimary
import com.example.imposter.ui.theme.AppSurface

@Composable
fun PlayerCountScreen(
    onContinueClick: (Int) -> Unit
) {
    var playerCount by remember { mutableIntStateOf(5) }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(AppBackground, Color(0xFF1F1F1F))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "How many players?",
                color = Color.White,
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "എത്ര പേർ കളിക്കുന്നു?",
                color = AppPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(56.dp))

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
                        containerColor = AppSurface,
                        contentColor = AppPrimary,
                        disabledContainerColor = AppSurface.copy(alpha = 0.5f)
                    ),
                    enabled = playerCount > 4,
                    modifier = Modifier.size(64.dp)
                ) {
                    Text(text = "–", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }

                // Number Display Card
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = AppSurface),
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(AppPrimary.copy(alpha = 0.5f), Color.Transparent))),
                    modifier = Modifier.size(110.dp)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = playerCount.toString(),
                            color = AppPrimary,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Plus Button
                FilledIconButton(
                    onClick = { if (playerCount < 15) playerCount++ },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = AppPrimary,
                        contentColor = AppBackground,
                        disabledContainerColor = AppSurface.copy(alpha = 0.5f)
                    ),
                    enabled = playerCount < 15,
                    modifier = Modifier.size(64.dp)
                ) {
                    Text(text = "+", fontSize = 28.sp, fontWeight = FontWeight.Bold)
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
                    containerColor = AppPrimary,
                    contentColor = AppBackground
                )
            ) {
                Text(text = "CONTINUE", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
