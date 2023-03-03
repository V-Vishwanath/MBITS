package com.vishwa.mbits.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun MBITSTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(),
        content = content
    )
}