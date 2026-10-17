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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onStartGameClick: () -> Unit,
    onHowToPlayClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF1F1F1F), Color(0xFF121212))
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
            text = "IMPOSTER",
            color = Color.White,
            style = MaterialTheme.typography.displayMedium,
            letterSpacing = 4.sp
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "ആരാണ് ഇംപോസ്റ്റർ?",
            color = Color(0xFFFFD700),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "ഒരാൾക്ക് വാക്ക് അറിയില്ല...\nഅവൻ ആരാണ്?",
            color = Color.LightGray,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )
        
        Spacer(modifier = Modifier.height(64.dp))

        MenuButton(text = "🎮 START GAME", onClick = onStartGameClick)
        Spacer(modifier = Modifier.height(16.dp))
        MenuButton(text = "📖 HOW TO PLAY", onClick = onHowToPlayClick)
        Spacer(modifier = Modifier.height(16.dp))
        MenuButton(text = "⚙ SETTINGS", onClick = onSettingsClick)
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF25623),
            contentColor = Color.White
        )
    ) {
        Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}
