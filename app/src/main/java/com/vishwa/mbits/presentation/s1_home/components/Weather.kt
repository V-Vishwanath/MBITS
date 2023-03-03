package com.vishwa.mbits.presentation.s1_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vishwa.mbits.R

@Composable
fun WeatherNotification() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Green.copy(alpha = 0.3f))
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "Notification"
        )

        Spacer(modifier = Modifier.width(4.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Weather")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "There will be addition of 500 ml to 1 Litre of water to your daily intake based on the weather temperature",
            )
        }

        var checkState by remember { mutableStateOf(true) }
        Switch(
            checked = checkState,
            onCheckedChange = { checkState = it }
        )

    }
}