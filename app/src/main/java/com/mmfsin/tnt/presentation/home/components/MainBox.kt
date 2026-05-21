package com.mmfsin.tnt.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.HomeTypeClassification.MY_PRODUCTS
import com.mmfsin.tnt.presentation.core.theme.Black
import com.mmfsin.tnt.presentation.core.theme.White

@Preview
@Composable
fun MainBoxPV() {
    MainBox { }
}

@Composable
fun MainBox(navigateTo:()-> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(220.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { navigateTo() })
            .background(White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(MY_PRODUCTS.pngBackground), null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(modifier = Modifier.fillMaxSize().alpha(0.20f).background(Black))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(R.string.home_box_my_products),
                style = MaterialTheme.typography.titleLarge,
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = stringResource(R.string.home_box_my_products_all_i_have),
                style = MaterialTheme.typography.bodySmall,
                color = White,
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}