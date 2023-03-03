package com.vishwa.mbits.presentation.s1_home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vishwa.mbits.presentation.destinations.GoalsScreenDestination

@Composable
private fun BottomSheetButton(rId: Int, title: String, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = rId),
                contentDescription = title
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = title, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = text, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ButtonSheetButtons(
    navigator: DestinationsNavigator,
    isCollapsed: Boolean,
    sheetButtons: List<Triple<Int, String, String>>,
) {
    val numItems = if (isCollapsed) 2 else sheetButtons.size

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(numItems) {index ->
            val item = sheetButtons[index]
            BottomSheetButton(rId = item.first, title = item.second, text = item.third) {
                if (item.second != "Goal") return@BottomSheetButton
                navigator.navigate(GoalsScreenDestination(text = item.third))
            }
        }
    }
}