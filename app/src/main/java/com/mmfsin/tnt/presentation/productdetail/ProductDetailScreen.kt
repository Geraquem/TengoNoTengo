package com.mmfsin.tnt.presentation.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.core.theme.RedMedium
import com.mmfsin.tnt.presentation.productdetail.components.DeleteProductDialog

@Preview
@Composable
fun ProductDetailPV() {
    ProductDetailContent(
        uiState = ProductDetailStates(
            product = Product(
                id = "",
                name = "",
                info = "",
                whereToFind = "",
                haveIt = false,
                favorite = true,
                date = 0
            ),
            deleteDialog = true
        ),
        {}, {}, {}, {},
        {}, {},
    )
}

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    goBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductDetailContent(
        uiState = uiState,
        goBack = goBack,
        nameChanged = { viewModel.onNameChanged(it) },
        whereToChanged = { viewModel.onWhereToChanged(it) },
        infoChanged = { viewModel.onInfoChanged(it) },
        updateDeleteDialogVisibility = { viewModel.updateDeleteDialogVisibility() },
        deleteProduct = { viewModel.deleteProduct() }
    )
}

@Composable
fun ProductDetailContent(
    uiState: ProductDetailStates,
    goBack: () -> Unit,
    nameChanged: (String) -> Unit,
    whereToChanged: (String) -> Unit,
    infoChanged: (String) -> Unit,
    updateDeleteDialogVisibility: () -> Unit,
    deleteProduct: () -> Unit
) {
    Scaffold(
        topBar = { Toolbar(iconVisible = true, onBackClick = { goBack() }) }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding).background(GrayLight)) {

            if (uiState.finishAndGoBack) goBack()

            if (uiState.deleteDialog) {
                DeleteProductDialog(
                    uiState.newName,
                    onCancel = { updateDeleteDialogVisibility() },
                    onConfirm = { deleteProduct() }
                )
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                TitleText(R.string.product_detail_name)
                EditableText(uiState.newName, { nameChanged(it) })

                Spacer(Modifier.height(16.dp))

                SwitchBox(R.string.product_detail_have_it, checked = uiState.haveIt)

                Spacer(Modifier.height(16.dp))

                SwitchBox(R.string.product_detail_favorite, checked = uiState.isFavorite)

                Spacer(Modifier.height(16.dp))

                TitleText(R.string.product_detail_where)
                EditableText(text = uiState.newWhereTo, { whereToChanged(it) })

                Spacer(Modifier.height(16.dp))

                TitleText(R.string.product_detail_info)
                EditableText(text = uiState.newInfo, { infoChanged(it) }, maxLines = 20)

                Spacer(Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = { updateDeleteDialogVisibility() }) {
                        Text(
                            stringResource(R.string.product_detail_delete),
                            style = MaterialTheme.typography.bodyLarge,
                            color = RedMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(Modifier.width(24.dp))

                    TextButton(onClick = {}) {
                        Text(
                            stringResource(R.string.product_detail_save),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Blue,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun TitleText(text: Int) {
    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, bottom = 4.dp)
    )
}

@Composable
fun EditableText(
    text: String,
    onValueChange: (String) -> Unit,
    maxLines: Int = 1
) {
    BasicTextField(
        value = text, onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White).padding(12.dp),
        textStyle = MaterialTheme.typography.bodyLarge,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            imeAction = if (maxLines == 1) ImeAction.Next else ImeAction.None,
            capitalization = KeyboardCapitalization.Sentences
        ),
    )
}

@Composable
fun SwitchBox(text: Int, checked: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(text),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Switch(checked, onCheckedChange = {})
    }
}