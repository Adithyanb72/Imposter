package com.example.imposter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.imposter.R
import com.example.imposter.ui.theme.AppPrimary
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(
    onTimeout: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3000) // 3 seconds
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.asset_1),
            contentDescription = "Imposter Logo",
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
        )
    }
}
