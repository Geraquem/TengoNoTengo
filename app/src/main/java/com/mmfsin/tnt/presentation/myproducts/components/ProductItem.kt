package com.mmfsin.tnt.presentation.myproducts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.presentation.core.theme.GrayMedium
import com.mmfsin.tnt.presentation.core.theme.YellowLight

@Preview
@Composable
fun ProductItemPV() {
    ProductItem(
        Product(
            id = "", name = "Soja texturizada", info = "Muy rico para tener dos",
            whereToFind = null,
            haveIt = true, favorite = true
        ),
        isLast = false,
        { _, _ -> }
    )
}

@Composable
fun ProductItem(product: Product, isLast: Boolean, updateHaveIt: (String, Boolean) -> Unit) {
    Column(Modifier.fillMaxWidth().background(if (product.favorite) YellowLight else Color.White)) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                )
                product.whereToFind?.let { wtf ->
                    Row(Modifier.padding(top = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.my_products_where),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = wtf,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                product.info?.let { info ->
                    Text(
                        text = info,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            Spacer(Modifier.width(16.dp))
            Switch(product.haveIt, onCheckedChange = { updateHaveIt(product.id, it) })
        }
        if (!isLast) {
            Box(Modifier.fillMaxWidth().height(8.dp).background(GrayMedium))
        }
    }
}