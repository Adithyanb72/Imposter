package com.example.imposter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun SettingsScreen(
    soundEnabled: Boolean,
    onSoundChanged: (Boolean) -> Unit,
    vibrationEnabled: Boolean,
    onVibrationChanged: (Boolean) -> Unit,
    timerEnabled: Boolean,
    onTimerChanged: (Boolean) -> Unit,
    badWordsEnabled: Boolean,
    onBadWordsChanged: (Boolean) -> Unit,
    difficulty: String,
    onDifficultyChanged: (String) -> Unit,
    category: String,
    onCategoryChanged: (String) -> Unit,
    language: String,
    onLanguageChanged: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF1A1A2E), Color(0xFF16213E), Color(0xFF0F3460))
    )
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "⚙ SETTINGS",
            color = Color(0xFFFFD700),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        SettingDropdownItem(
            title = "Language / ഭാഷ",
            options = listOf("മലയാളം", "English"),
            selectedOption = language,
            onOptionSelected = onLanguageChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingSwitchItem(
            title = "Sound Effects / ശബ്ദം",
            checked = soundEnabled,
            onCheckedChange = onSoundChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingSwitchItem(
            title = "Vibration / വൈബ്രേഷൻ",
            checked = vibrationEnabled,
            onCheckedChange = onVibrationChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingSwitchItem(
            title = "Discussion Timer / ചർച്ചാ ടൈമർ",
            checked = timerEnabled,
            onCheckedChange = onTimerChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingSwitchItem(
            title = "Funny/Bad Words / തമാശ വാക്കുകൾ",
            checked = badWordsEnabled,
            onCheckedChange = onBadWordsChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingDropdownItem(
            title = "Difficulty / ബുദ്ധിമുട്ട്",
            options = listOf("Easy", "Medium", "Hard", "Mixed"),
            selectedOption = difficulty,
            onOptionSelected = onDifficultyChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingDropdownItem(
            title = "Category / വിഭാഗം",
            options = listOf("All", "FOOD", "KERALA", "ANIMALS", "NATURE", "HOME", "TRANSPORT"),
            selectedOption = category,
            onOptionSelected = onCategoryChanged
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE94560))
        ) {
            Text(text = "SAVE & BACK", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SettingSwitchItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x33FFFFFF)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFFFFD700),
                    checkedTrackColor = Color(0xFFE94560)
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingDropdownItem(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x33FFFFFF)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFFD700),
                        unfocusedBorderColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                onOptionSelected(option)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
