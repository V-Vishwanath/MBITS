package com.vishwa.mbits.presentation.s1_home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vishwa.mbits.R

@Composable
fun AlarmButton(time: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(),
        modifier = Modifier.size(200.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_alarm),
                    contentDescription = "Set Alarm"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Off")
            }
        }
    }
}