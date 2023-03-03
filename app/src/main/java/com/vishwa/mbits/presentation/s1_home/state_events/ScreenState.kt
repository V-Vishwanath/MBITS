package com.vishwa.mbits.presentation.s1_home.state_events

data class ScreenState(
    val time: Pair<Int, Int> = Pair(0, 0),
    val sheetButtons: List<Triple<Int, String, String>>,
    val sleepDisturbanceButtons: List<Pair<Int, String>>,
)