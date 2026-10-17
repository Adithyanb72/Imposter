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

@OptIn(ExperimentalMaterial3Api::class)
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
    
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF1A1A2E), Color(0xFF16213E), Color(0xFF0F3460))
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
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(playerNames) { index, name ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { newName ->
                            playerNames[index] = newName
                        },
                        label = { Text("Player ${index + 1}") },
                        placeholder = { Text("Enter name for Player ${index + 1}") },
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFD700),
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color(0xFFFFD700),
                            unfocusedLabelColor = Color.Gray,
                            cursorColor = Color(0xFFFFD700),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    if (playerNames.size > 4) {
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = { playerNames.removeAt(index) },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Text(text = "❌", fontSize = 16.sp)
                        }
                    }
                }
            }

            item {
                if (playerNames.size < 15) {
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedButton(
                        onClick = { playerNames.add("") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFFD700))
                    ) {
                        Text(text = "+ Add Player", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { 
                val finalNames = playerNames.mapIndexed { index, name -> 
                    val trimmed = name.trim()
                    if (trimmed.isEmpty()) "Player ${index + 1}" else trimmed
                }
                onStartGameClick(finalNames) 
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE94560),
                contentColor = Color.White
            )
        ) {
            Text(text = "GAME READY", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
