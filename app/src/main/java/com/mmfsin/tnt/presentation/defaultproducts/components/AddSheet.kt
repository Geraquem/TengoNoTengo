@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.tnt.presentation.defaultproducts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.mappers.createProductFromDefault
import com.mmfsin.tnt.domain.models.DefaultProduct
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.presentation.core.theme.BlueHard
import com.mmfsin.tnt.presentation.productdetail.components.SwitchBox

@Preview
@Composable
fun AddSheetPV() {
    AddSheet(
        onDismiss = {},
        defaultProduct = DefaultProduct(
            name = "Soja texturizada",
            haveIt = true
        ),
        {}
    )
}

@Composable
fun AddSheet(
    onDismiss: () -> Unit,
    defaultProduct: DefaultProduct,
    addProduct: (Product) -> Unit
) {

    var haveIt by remember { mutableStateOf(defaultProduct.haveIt) }
    var favorite by remember { mutableStateOf(defaultProduct.favorite) }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(16.dp))

            Text(
                defaultProduct.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            Spacer(Modifier.height(24.dp))

            SwitchBox(R.string.product_detail_have_it, haveIt, { haveIt = !haveIt }, isFav = false)
            Spacer(Modifier.height(8.dp))
            SwitchBox(R.string.product_detail_favorite, favorite, { favorite = !favorite }, isFav = true)

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { onDismiss() }) {
                    Text(
                        text = stringResource(R.string.df_products_close),
                        style = MaterialTheme.typography.bodyLarge,
                        color = BlueHard
                    )
                }
                TextButton(
                    onClick = { addProduct(createProductFromDefault(defaultProduct.name, haveIt, favorite)) }
                ) {
                    Text(
                        text = stringResource(R.string.df_products_add),
                        style = MaterialTheme.typography.bodyLarge,
                        color = BlueHard
                    )
                }
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}