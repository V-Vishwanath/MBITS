package com.vishwa.mbits.presentation.s2_row_menu_replace.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DisturbanceButton(
    rId: Int,
    text: String,
    bgColor: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = bgColor)
    ) {
        Icon(painter = painterResource(id = rId), contentDescription = text)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text, modifier = Modifier.weight(1f))
    }
}