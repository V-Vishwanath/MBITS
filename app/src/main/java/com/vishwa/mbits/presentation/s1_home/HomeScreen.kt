package com.vishwa.mbits.presentation.s1_home

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultRecipient
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import com.vishwa.mbits.presentation.*
import com.vishwa.mbits.presentation.destinations.GoalsScreenDestination
import com.vishwa.mbits.presentation.destinations.SleepDisturbancesReplaceScreenDestination
import com.vishwa.mbits.presentation.s1_home.components.*
import com.vishwa.mbits.presentation.s1_home.state_events.ScreenEvent
import com.vishwa.mbits.presentation.s1_home.state_events.ScreenState
import java.util.*
import androidx.lifecycle.viewmodel.compose.viewModel as composeVM

@OptIn(ExperimentalMaterialApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: ScreenVM = composeVM(LocalContext.current as ComponentActivity),

    disturbancesResult: ResultRecipient<SleepDisturbancesReplaceScreenDestination, Bundle>,
    goalSelectedResult: ResultRecipient<GoalsScreenDestination, String>
) {
    val state: ScreenState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        val cal = Calendar.getInstance()
        val time = Pair(
            cal[Calendar.HOUR_OF_DAY],
            cal[Calendar.MINUTE]
        )

        viewModel.onEvent(ScreenEvent.OnTimeChanged(time))
    }

    disturbancesResult.onNavResult {
        when (it) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                val selected = it.value.getInt("selected", -1)
                val replaceWith = it.value.getInt("replaceWith")
                viewModel.onEvent(
                    ScreenEvent.OnSleepDisturbanceChanged(
                        selectedId = selected,
                        replaceWith = replaceWith
                    )
                )
            }
        }
    }

    goalSelectedResult.onNavResult {
        when (it) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> viewModel.onEvent(ScreenEvent.OnGoalChanged(goal = it.value))
        }
    }

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hour: Int, min: Int ->
            viewModel.onEvent(ScreenEvent.OnTimeChanged(Pair(hour, min)))
        },
        state.time.first, state.time.second,
        false
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = Color.LightGray,
        sheetPeekHeight = 328.dp,
        sheetContent = {
            BottomSheetContent(
                navigator = navigator,
                sheetButtons = state.sheetButtons,
                sleepDisturbanceButtons = state.sleepDisturbanceButtons,
                isCollapsed = sheetState.currentValue == BottomSheetValue.Collapsed
            )
        }
    ) {
        SleepAppBar(title = "Sleep Tool")

        Column(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            AlarmButton(time = "%02d:%02d".format(state.time.first, state.time.second)) {
                timePickerDialog.show()
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        brush = SolidColor(Color.Black),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                ProgressView(time = "8 hrs", text = "Recommended")
                ProgressView(time = "7 hrs", text = "Goal")
                ProgressView(time = "6 hrs", text = "Achieved")

            }

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MBITSTheme {
        HomeScreen(
            navigator = EmptyDestinationsNavigator,
            disturbancesResult = EmptyResultRecipient(),
            goalSelectedResult = EmptyResultRecipient()
        )
    }
}