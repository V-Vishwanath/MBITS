package com.vishwa.mbits.presentation.s1_home.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vishwa.mbits.R

@Composable
fun AppBarActionButton(
    painter: Painter,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(painter = painter, contentDescription = description)
    }
}

@Composable
fun SleepAppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        actions = {
            AppBarActionButton(
                painter = painterResource(id = R.drawable.ic_support),
                description = "Support"
            ) {

            }
        },
    )
}