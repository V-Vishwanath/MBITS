package com.vishwa.mbits.presentation.s1_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vishwa.mbits.presentation.destinations.SleepDisturbancesReplaceScreenDestination

@Composable
fun BottomSheetContent(
    navigator: DestinationsNavigator,
    isCollapsed: Boolean,
    sheetButtons: List<Triple<Int, String, String>>,
    sleepDisturbanceButtons: List<Pair<Int, String>>,
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, false)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Practice",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow {
                items(sleepDisturbanceButtons) { (rId, text) ->
                    SleepDisorderButton(rId = rId, text = text) {
                        navigator.navigate(
                            SleepDisturbancesReplaceScreenDestination(selectedItemRId = rId)
                        )
                    }
                }

                item {
                    SleepDisorderButton(rId = null, text = "More") {

                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            ButtonSheetButtons(
                navigator = navigator,
                isCollapsed = isCollapsed,
                sheetButtons = sheetButtons
            )

            if (!isCollapsed) {
                Spacer(modifier = Modifier.height(16.dp))
                WeatherNotification()
            }
        }

        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(horizontal = 16.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Schedule")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Start")
            }
        }
    }
}