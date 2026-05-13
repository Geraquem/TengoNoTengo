package com.mmfsin.tnt.presentation.home.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.theme.White

@Preview
@Composable
fun CategoryIcon() {
    Icon(
        painterResource(R.drawable.ic_category), null,
        tint = White,
        modifier = Modifier.size(46.dp)
    )
}