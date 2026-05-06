package com.mmfsin.tnt.presentation.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.theme.GrayHard
import com.mmfsin.tnt.presentation.core.theme.GrayMedium
import com.mmfsin.tnt.presentation.core.theme.GreenMedium
import com.mmfsin.tnt.presentation.core.theme.White
import com.mmfsin.tnt.presentation.core.theme.YellowHard
import com.mmfsin.tnt.presentation.core.theme.YellowLight
import com.mmfsin.tnt.presentation.core.theme.YellowMedium

@Preview
@Composable
fun SwitchPV() {
    Column {
        SwitchHaveIt(true) {}
        SwitchHaveIt(false) {}
        SwitchFavorite(true) {}
        SwitchFavorite(false) {}
    }
}

@Composable
fun SwitchHaveIt(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(
        checked, { onCheckedChange(it) },
        thumbContent = {
            val icon = if (checked) R.drawable.ic_check else R.drawable.ic_shop_cart
            Icon(
                painterResource(icon), null,
                modifier = Modifier.padding(if (checked) 2.dp else 4.dp),
                tint = if (checked) GreenMedium else White
            )
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = White,
            checkedTrackColor = GreenMedium,
            uncheckedThumbColor = GrayHard,
            uncheckedTrackColor = GrayMedium,
            uncheckedBorderColor = GrayHard
        ),
    )
}

@Composable
fun SwitchFavorite(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(
        checked, { onCheckedChange(it) },
        thumbContent = {
            val icon = if (checked) R.drawable.ic_fav else R.drawable.ic_cross
            Icon(
                painterResource(icon), null,
                modifier = Modifier.padding(if (checked) 0.dp else 2.dp),
                tint = if (checked) YellowHard else White
            )
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = White,
            checkedTrackColor = YellowHard,
            uncheckedThumbColor = GrayHard,
            uncheckedTrackColor = GrayMedium,
            uncheckedBorderColor = GrayHard
        ),
    )
}