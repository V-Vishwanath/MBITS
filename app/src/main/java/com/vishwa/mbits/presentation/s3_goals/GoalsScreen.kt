package com.vishwa.mbits.presentation.s3_goals

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.vishwa.mbits.R
import com.vishwa.mbits.presentation.MBITSTheme
import com.vishwa.mbits.presentation.ScreenVM
import com.vishwa.mbits.presentation.s1_home.components.SleepAppBar
import com.vishwa.mbits.presentation.s3_goals.components.GoalCard
import androidx.lifecycle.viewmodel.compose.viewModel as composeVM

@Destination
@Composable
fun GoalsScreen(
    text: String,
    resultNavigator: ResultBackNavigator<String>,
    viewModel: ScreenVM = composeVM(LocalContext.current as ComponentActivity)
) {
    var selected by remember { mutableStateOf(text) }

    Scaffold(
        topBar = { SleepAppBar(title = "Goals") },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .padding(top = 16.dp),
        ) {
            Text(
                text = "Select the Goal you want to achieve",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(viewModel.goals) { goal ->
                    GoalCard(goal = goal, selected = goal == selected) {
                        selected = goal
                    }
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(8.dp),
                onClick = {
                    resultNavigator.navigateBack(selected)
                },
            ) {
                Text(text = "Done")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

@Preview
@Composable
fun GoalsScreenPreview() {
    MBITSTheme {
        GoalsScreen(
            text = "De-Stress",
            resultNavigator = EmptyResultBackNavigator()
        )
    }
}