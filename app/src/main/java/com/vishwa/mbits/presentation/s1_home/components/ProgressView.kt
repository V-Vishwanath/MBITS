package com.vishwa.mbits.presentation.s1_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProgressView(time: String, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = time,
            fontSize = 14.sp
        )

        Box(
            modifier = Modifier
                .width(90.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray)
        )

        Text(
            text = text,
            fontSize = 14.sp
        )
    }

}