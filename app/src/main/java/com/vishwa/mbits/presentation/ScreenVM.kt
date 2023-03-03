package com.vishwa.mbits.presentation

import androidx.lifecycle.ViewModel
import com.vishwa.mbits.R
import com.vishwa.mbits.presentation.s1_home.state_events.ScreenEvent
import com.vishwa.mbits.presentation.s1_home.state_events.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScreenVM : ViewModel() {
    val sleepDisturbances = listOf(
        Pair(R.drawable.ic_dream, "Dream"),
        Pair(R.drawable.ic_kids, "Kids"),
        Pair(R.drawable.ic_love, "Love"),
        Pair(R.drawable.ic_water, "Water"),
        Pair(R.drawable.ic_toilet, "Toilet"),
    )

    val goals = listOf(
        "De-Stress",
        "Fall Asleep",
        "Take a Break",
        "Clear your Mind"
    )

    private val sheetButtons = mutableListOf(
        Triple(R.drawable.ic_love, "Dream", "Calm"),
        Triple(R.drawable.ic_love, "Goal", "De-Stress"),
        Triple(R.drawable.ic_love, "Factors", "Sleep Factors"),
        Triple(R.drawable.ic_love, "Jet Lag", "Check Tips"),
        Triple(R.drawable.ic_love, "Sleep Stories", "MoonLight")
    )

    private val _state = MutableStateFlow(
        ScreenState(
            sheetButtons = sheetButtons,
            sleepDisturbanceButtons = sleepDisturbances.take(3),
        )
    )

    val state = _state.asStateFlow()

    fun onEvent(event: ScreenEvent) = when(event) {
        is ScreenEvent.OnTimeChanged -> _state.update { it.copy(time = event.time) }

        is ScreenEvent.OnSleepDisturbanceChanged -> {
            val replaceItem = sleepDisturbances.find { it.first == event.replaceWith }!!
            _state.update {
                val newList = it.sleepDisturbanceButtons.map { item ->
                    if (item.first != event.selectedId) return@map item
                    return@map replaceItem
                }

                it.copy(sleepDisturbanceButtons = newList)
            }
        }

        is ScreenEvent.OnGoalChanged -> {
            sheetButtons[1] = sheetButtons[1].copy(third = event.goal)
            _state.update { it.copy(sheetButtons = sheetButtons) }
        }
    }
}