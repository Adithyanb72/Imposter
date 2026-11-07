package com.example.imposter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imposter.ui.theme.AppBackground
import com.example.imposter.ui.theme.AppPrimary
import com.example.imposter.ui.theme.AppSecondaryText
import com.example.imposter.ui.theme.AppSurface

@Composable
fun HomeScreen(
    onStartGameClick: () -> Unit,
    onHowToPlayClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
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
            Row {
                Text(
                    text = "IMPOS",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "TER",
                    color = AppPrimary,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "FIND THE IMPOSTER ?",
                color = AppPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Spacer(modifier = Modifier.height(56.dp))

            MenuButton(text = "START GAME", isPrimary = true, onClick = onStartGameClick)
            Spacer(modifier = Modifier.height(16.dp))
            MenuButton(text = "HOW TO PLAY", isPrimary = false, onClick = onHowToPlayClick)
            Spacer(modifier = Modifier.height(16.dp))
            MenuButton(text = "⚙  SETTINGS", isPrimary = false, onClick = onSettingsClick)
        }
    }
}

@Composable
fun MenuButton(text: String, isPrimary: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isPrimary) AppPrimary else AppSurface,
            contentColor = if (isPrimary) AppBackground else Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = if (isPrimary) 8.dp else 2.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp
        )
    }
}
