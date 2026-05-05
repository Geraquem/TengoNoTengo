package com.mmfsin.tnt.presentation.myproducts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.theme.GrayMedium

@Preview
@Composable
fun ProductFilterPV() {
    ProductFilter()
}

@Composable
fun ProductFilter() {
    Column(Modifier.fillMaxWidth().background(Color.White)) {
        Row(
            modifier = Modifier.fillMaxWidth().clickable(onClick = {}).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.my_products_sort_by),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.Blue
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "Favoritos primero",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Box(Modifier.fillMaxWidth().height(8.dp).background(GrayMedium))
    }
}