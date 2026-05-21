package com.mmfsin.tnt.presentation.defaultproducts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.tnt.domain.models.DefaultProduct
import com.mmfsin.tnt.presentation.core.theme.GreenLight
import com.mmfsin.tnt.presentation.core.theme.White

@Preview
@Composable
fun DefaultProductBoxPV() {
    DefaultProductBox(
        defaultProduct = DefaultProduct(
            name = "Soja texturizada",
            exists = true
        ),
        {}
    )
}

@Composable
fun DefaultProductBox(
    defaultProduct: DefaultProduct,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onClick() })
            .background(if (defaultProduct.exists) GreenLight else White)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = defaultProduct.name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}