package com.mmfsin.tnt.presentation.myproducts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.theme.GrayMedium

@Preview
@Composable
fun AddProductPV() {
    AddProduct("Hola holita vecinito") {}
}

@Composable
fun AddProduct(product: String, onValueChange: (String) -> Unit) {
    Column(Modifier.fillMaxWidth().background(Color.White).padding(top = 6.dp, bottom = 28.dp, start = 16.dp, end = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                stringResource(R.string.my_products_add_product), style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(Modifier.weight(1f))
            TextButton(onClick = {}) {
                Text(
                    stringResource(R.string.my_products_advanced_btn), style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Blue
                )
            }
        }
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(GrayMedium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                value = product, onValueChange = { onValueChange(it) },
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f).padding(start = 18.dp)
            )

            Spacer(Modifier.width(2.dp))

            IconButton(onClick = {}) {
                Icon(
                    painterResource(R.drawable.ic_add), stringResource(R.string.my_products_add_btn),
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}
