@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.tnt.presentation.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mmfsin.tnt.R

@Preview
@Composable
fun ToolbarPV() {
    Toolbar(true, R.string.app_name)
}

@Composable
fun Toolbar(iconVisible: Boolean = false, text: Int = R.string.empty, onClick: () -> Unit = {}) {
    TopAppBar(
        modifier = Modifier
            .zIndex(1f)
            .shadow(
                elevation = 8.dp,
                clip = false
            ).background(Color.White).padding(horizontal = 16.dp),
        title = { Text(text = stringResource(text)) },
        navigationIcon = {
            if (iconVisible) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.cd_arrow_back),
                    modifier = Modifier.padding(end = 8.dp).size(30.dp).clickable(onClick = { onClick() })
                )
            }
        }
    )
}