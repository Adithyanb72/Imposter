package com.example.imposter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.imposter.ui.theme.AppSecondaryText
import com.example.imposter.ui.theme.AppSurface

@Composable
fun PlayerNamesScreen(
    playerCount: Int,
    initialNames: List<String> = emptyList(),
    onStartGameClick: (List<String>) -> Unit
) {
    val playerNames = remember { 
        mutableStateListOf<String>().apply {
            val count = if (initialNames.isNotEmpty()) initialNames.size else playerCount
            for (i in 0 until count) {
                add(if (i < initialNames.size) initialNames[i] else "")
            }
            if (isEmpty()) {
                for (i in 0 until playerCount) {
                    add("")
                }
            }
        }
    }
    
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(AppBackground, Color(0xFF1F1F1F))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .imePadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter Player Names",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(playerNames) { index, name ->
                val isEmpty = name.trim().isEmpty()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { newName ->
                            playerNames[index] = newName
                            if (errorMessage != null) errorMessage = null
                        },
                        label = { Text("Player ${index + 1}") },
                        placeholder = { Text("Enter name for Player ${index + 1}") },
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                        isError = errorMessage != null && isEmpty,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (errorMessage != null && isEmpty) Color(0xFFFF5252) else AppPrimary,
                            unfocusedBorderColor = if (errorMessage != null && isEmpty) Color(0xFFFF5252) else Color(0xFF333333),
                            focusedLabelColor = if (errorMessage != null && isEmpty) Color(0xFFFF5252) else AppPrimary,
                            unfocusedLabelColor = AppSecondaryText,
                            cursorColor = AppPrimary,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = AppSurface,
                            unfocusedContainerColor = AppSurface
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    if (playerNames.size > 4) {
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = { 
                                playerNames.removeAt(index) 
                                if (errorMessage != null) errorMessage = null
                            },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Text(text = "✕", fontSize = 16.sp, color = Color(0xFFFF5252))
                        }
                    }
                }
            }

            item {
                if (playerNames.size < 15) {
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedButton(
                        onClick = { playerNames.add("") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = AppPrimary),
                        border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.linearGradient(listOf(AppPrimary, AppPrimary)))
                    ) {
                        Text(text = "+ Add Player", fontSize = 15.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
        
        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage ?: "",
                color = Color(0xFFFF5252),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { 
                val emptyIndex = playerNames.indexOfFirst { it.trim().isEmpty() }
                if (emptyIndex != -1) {
                    errorMessage = "Please enter name for Player ${emptyIndex + 1}"
                } else {
                    errorMessage = null
                    val finalNames = playerNames.map { it.trim() }
                    onStartGameClick(finalNames)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppPrimary,
                contentColor = AppBackground
            )
        ) {
            Text(text = "GAME READY", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}
