package com.mmfsin.tnt.presentation.productdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.theme.RedMedium
import com.mmfsin.tnt.presentation.utils.middleBoldText

@Preview
@Composable
fun DeleteProductDialogPV() {
    DeleteProductDialog("Soja texturizada", {}, {})
}

@Composable
fun DeleteProductDialog(productName: String, onCancel: () -> Unit, onConfirm: () -> Unit) {
    Dialog(onDismissRequest = { onCancel() }) {
        Column(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White)
                .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 8.dp)
        ) {
            Text(
                text = middleBoldText(R.string.delete_product, productName),
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { onCancel() }) {
                    Text(
                        stringResource(R.string.delete_product_cancel),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.width(16.dp))

                TextButton(onClick = { onConfirm() }) {
                    Text(
                        stringResource(R.string.delete_product_confirm),
                        style = MaterialTheme.typography.bodyLarge,
                        color = RedMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}