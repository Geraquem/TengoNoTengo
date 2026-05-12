package com.mmfsin.tnt.presentation.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mmfsin.tnt.presentation.core.theme.GrayHard
import com.mmfsin.tnt.presentation.core.theme.GreenMedium


@Preview
@Composable
fun MyRadioButtonPV() {
    Column() {
        MyRadioButton(true) { }
        MyRadioButton(false) { }
    }
}

@Composable
fun MyRadioButton(selected: Boolean, onClick: () -> Unit) {
    RadioButton(
        selected = selected,
        onClick = onClick,
        colors = RadioButtonDefaults.colors(
            selectedColor = GreenMedium,
            unselectedColor = GrayHard
        ),

        )
}
