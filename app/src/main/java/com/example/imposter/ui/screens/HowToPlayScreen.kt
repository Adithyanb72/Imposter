package com.example.imposter.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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

data class TutorialStep(
    val stepNumber: Int,
    val titleEn: String,
    val titleMl: String,
    val descEn: String,
    val descMl: String
)

@Composable
fun HowToPlayScreen(
    onBackClick: () -> Unit
) {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(AppBackground, Color(0xFF1F1F1F))
    )

    val steps = listOf(
        TutorialStep(
            1,
            "Players Join",
            "കളിക്കാർ ചേരുന്നു",
            "Enter the names of 4 to 15 players.",
            "4 മുതൽ 15 വരെയുള്ള കളിക്കാരുടെ പേരുകൾ നൽകുക."
        ),
        TutorialStep(
            2,
            "Receive Secret Card",
            "സീക്രട്ട് കാർഡ് ലഭിക്കുന്നു",
            "Everyone receives a secret card by passing the phone.",
            "ഫോൺ കൈമാറി എല്ലാവരും രഹസ്യ കാർഡ് പരിശോധിക്കുക."
        ),
        TutorialStep(
            3,
            "The Imposter",
            "ഇംപോസ്റ്റർ",
            "One player is secretly selected as the Imposter and gets no word.",
            "ഒരാൾ രഹസ്യമായി ഇംപോസ്റ്റർ ആകുന്നു, അയാൾക്ക് വാക്ക് ലഭിക്കില്ല."
        ),
        TutorialStep(
            4,
            "Discussion",
            "ചർച്ച",
            "Discuss with each other and try to figure out who the imposter is.",
            "പരസ്പരം സംസാരിച്ച് ഇംപോസ്റ്റർ ആരാണെന്ന് കണ്ടെത്താൻ ശ്രമിക്കുക."
        ),
        TutorialStep(
            5,
            "Reveal Imposter",
            "ഇംപോസ്റ്ററെ കണ്ടെത്തൽ",
            "Reveal the imposter and check if you caught them!",
            "ഇംപോസ്റ്ററെ വെളിപ്പെടുത്തി നിങ്ങൾ അവരെ പിടികൂടിയോ എന്ന് നോക്കുക."
        ),
        TutorialStep(
            6,
            "Final Guess",
            "അവസാന അവസരം",
            "If caught, the imposter gets one final chance to guess the secret word!",
            "പിടികൂടിയാൽ, ഇംപോസ്റ്റർക്ക് രഹസ്യ വാക്ക് ഊഹിക്കാൻ ഒരു അവസാന അവസരം ലഭിക്കും."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "📖 HOW TO PLAY",
            color = AppPrimary,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(steps) { _, step ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = AppSurface),
                    border = BorderStroke(1.dp, Color(0x33A2FF00)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = AppPrimary,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${step.stepNumber}",
                                        color = AppBackground,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = step.titleEn,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = step.titleMl,
                                    color = AppPrimary,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = step.descEn,
                            color = AppSecondaryText,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = step.descMl,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppPrimary, contentColor = AppBackground)
        ) {
            Text(text = "BACK TO HOME", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}
