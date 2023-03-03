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

@Composable
fun SleepDisorderButton(rId: Int?, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.size(64.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            rId?.let {
                Icon(
                    painter = painterResource(id = rId),
                    contentDescription = text,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))
            }

            Text(
                text = text,
                fontSize = 12.sp
            )
        }
    }

    Spacer(modifier = Modifier.width(8.dp))
}