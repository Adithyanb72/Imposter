package com.example.imposter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    var soundEnabled by mutableStateOf(true)
    var vibrationEnabled by mutableStateOf(true)
    var timerEnabled by mutableStateOf(true)
    var badWordsEnabled by mutableStateOf(false)
    var selectedDifficulty by mutableStateOf("Mixed")
    var selectedCategory by mutableStateOf("All")
    var selectedLanguage by mutableStateOf("മലയാളം")
}
