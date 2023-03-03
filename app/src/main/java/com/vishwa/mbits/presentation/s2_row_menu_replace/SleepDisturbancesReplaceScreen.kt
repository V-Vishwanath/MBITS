package com.vishwa.mbits.presentation.s2_row_menu_replace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.vishwa.mbits.R
import com.vishwa.mbits.presentation.MBITSTheme
import com.vishwa.mbits.presentation.s1_home.state_events.ScreenState
import com.vishwa.mbits.presentation.ScreenVM
import com.vishwa.mbits.presentation.s1_home.components.SleepAppBar
import com.vishwa.mbits.presentation.s2_row_menu_replace.components.DisturbanceButton
import androidx.lifecycle.viewmodel.compose.viewModel as composeVM

@Destination
@Composable
fun SleepDisturbancesReplaceScreen(
    selectedItemRId: Int,
    resultNavigator: ResultBackNavigator<Bundle>,
    viewModel: ScreenVM = composeVM(LocalContext.current as ComponentActivity)
) {
    val state: ScreenState by viewModel.state.collectAsState()
    var replacement by remember { mutableStateOf(selectedItemRId) }

    Scaffold(
        topBar = { SleepAppBar(title = "Sleep Disturbances") },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .padding(top = 16.dp)
        ) {

            Text(
                text = "Tap to add/remove the reason for sleep disturbances",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                item {
                    DisturbanceButton(
                        rId = R.drawable.ic_add,
                        text = "Add Other"
                    ) {}
                }

                items(viewModel.sleepDisturbances) { (rId, text) ->
                    val itemThere = state.sleepDisturbanceButtons.any { item ->
                        item.first == rId
                    }

                    val color = when {
                        replacement == rId -> Color.Green
                        itemThere -> Color.LightGray
                        else -> MaterialTheme.colors.primary
                    }

                    DisturbanceButton(rId = rId, text = text, bgColor = color) {
                        if (itemThere) return@DisturbanceButton
                        replacement = rId
                    }
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(8.dp),
                onClick = {
                    val res = bundleOf(
                        "selected" to selectedItemRId,
                        "replaceWith" to replacement
                    )

                    resultNavigator.navigateBack(res)
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
fun SleepDisturbancesReplaceScreenPreview() {
    MBITSTheme {
        SleepDisturbancesReplaceScreen(
            selectedItemRId = R.drawable.ic_love,
            resultNavigator = EmptyResultBackNavigator(),
        )
    }
}