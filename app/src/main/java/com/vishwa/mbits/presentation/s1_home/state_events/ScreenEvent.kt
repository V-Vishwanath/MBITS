package com.vishwa.mbits.presentation.s1_home.state_events

sealed class ScreenEvent {
    data class OnTimeChanged(val time: Pair<Int, Int>): ScreenEvent()
    data class OnSleepDisturbanceChanged(val selectedId: Int, val replaceWith: Int): ScreenEvent()
    data class OnGoalChanged(val goal: String): ScreenEvent()
}
