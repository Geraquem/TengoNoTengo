@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.tnt.presentation.core.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    Toolbar(true, R.string.app_name, {}, R.drawable.ic_sort)
}

@Composable
fun Toolbar(
    iconVisible: Boolean = false,
    text: Int = R.string.empty,
    onBackClick: () -> Unit = {},
    rightIcon: Int? = null,
    onRightIconClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = Modifier
            .zIndex(1f)
            .shadow(
                elevation = 8.dp,
                clip = false
            ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(text),
                    modifier = Modifier.weight(1f).padding(start = if (iconVisible) 2.dp else 16.dp)
                )

                if (rightIcon != null) {
                    IconButton(onClick = onRightIconClick, modifier = Modifier.padding(start = 0.dp)) {
                        Icon(
                            painter = painterResource(rightIcon),
                            contentDescription = stringResource(R.string.cd_sort),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        },

        navigationIcon = {
            if (iconVisible) {
                IconButton(onClick = onBackClick, modifier = Modifier.padding(start = 0.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = stringResource(R.string.cd_arrow_back),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    )
}