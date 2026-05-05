package com.mmfsin.tnt.presentation.myproducts.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.theme.GrayMedium

@Preview
@Composable
fun AddProductPV() {
    AddProduct(
        isVisible = true,
        product = "Hola holita vecinito",
        {}, {}, {}, {}
    )
}

@Composable
fun AddProduct(
    isVisible: Boolean,
    product: String,
    onValueChange: (String) -> Unit,
    addProduct: (String) -> Unit,
    advancedMode: (String) -> Unit,
    changeVisibility: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val scaleIcon by animateFloatAsState(
        targetValue = if (isVisible) 1f else -1f,
        label = ""
    )

    Column(
        modifier = modifier.fillMaxWidth()
            .background(Color.White)
            .animateContentSize()
            .padding(bottom = 28.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.my_products_add_product), style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 12.dp)
                )
                IconButton(onClick = { changeVisibility() }) {
                    Icon(
                        painterResource(R.drawable.ic_arrow_down), stringResource(R.string.cd_arrow_down),
                        modifier = Modifier.graphicsLayer { scaleY = scaleIcon }
                    )
                }
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TextButton(onClick = { advancedMode(product) }) {
                    Text(
                        stringResource(R.string.my_products_advanced_btn), style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Blue
                    )
                }
            }
        }
        if (isVisible) {
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(GrayMedium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicTextField(
                    value = product, onValueChange = { onValueChange(it) },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f).padding(start = 18.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )

                Spacer(Modifier.width(2.dp))

                IconButton(onClick = { addProduct(product) }) {
                    Icon(
                        painterResource(R.drawable.ic_add), stringResource(R.string.my_products_add_btn),
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        }
    }
}
