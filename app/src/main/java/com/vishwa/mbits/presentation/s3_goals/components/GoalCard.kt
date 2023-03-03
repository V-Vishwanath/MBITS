package com.vishwa.mbits.presentation.s3_goals.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vishwa.mbits.R

@Composable
fun GoalCard(goal: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {

        Image(
            painter = painterResource(id = R.drawable.yoga),
            contentDescription = goal,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_love),
            contentDescription = "Selected",
            tint = if (selected) Color.Red else Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = goal,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Nighttime",
                color = Color.White
            )
        }
    }
}