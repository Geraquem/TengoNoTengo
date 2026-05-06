package com.mmfsin.tnt.presentation.productdetail

import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.presentation.core.components.SwitchFavorite
import com.mmfsin.tnt.presentation.core.components.SwitchHaveIt
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.BlueMedium
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.core.theme.RedMedium
import com.mmfsin.tnt.presentation.core.theme.White
import com.mmfsin.tnt.presentation.core.theme.YellowLight
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
            newName = "Soja texturizada",
            isFavorite = false,
            haveIt = true,
            deleteDialog = false
        ),
        {}, {}, {}, {}, {},
        {}, {}, {}, {},
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
        deleteProduct = { viewModel.deleteProduct() },
        updateHaveIt = { viewModel.updateHaveIt(it) },
        updateFavorite = { viewModel.updateFavorite(it) },
        saveAndUpdateProduct = { viewModel.saveAndUpdateProduct() }
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
    deleteProduct: () -> Unit,
    updateHaveIt: (Boolean) -> Unit,
    updateFavorite: (Boolean) -> Unit,
    saveAndUpdateProduct: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = { Toolbar(onBackClick = { goBack() }) }
    ) { innerPadding ->
        Box(
            Modifier.fillMaxSize()
                .padding(innerPadding)
                .background(GrayLight)
                .windowInsetsPadding(WindowInsets.ime)
        ) {

            if (uiState.sww) {
                /** ERROR */
            }

            if (uiState.finishAndGoBack) goBack()

            if (uiState.deleteDialog) {
                DeleteProductDialog(
                    uiState.newName,
                    onCancel = { updateDeleteDialogVisibility() },
                    onConfirm = { deleteProduct() }
                )
            }
            CompositionLocalProvider(LocalOverscrollFactory provides null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 20.dp).padding(bottom = 80.dp)
                ) {
                    TitleText(R.string.product_detail_name)
                    MainEditableText(
                        uiState.newName, { nameChanged(it) },
                        isFav = uiState.isFavorite
                    )

                    Spacer(Modifier.height(16.dp))

                    SwitchBox(
                        text = R.string.product_detail_have_it,
                        checked = uiState.haveIt,
                        updateState = { updateHaveIt(it) },
                        isFav = false
                    )

                    Spacer(Modifier.height(8.dp))

                    SwitchBox(
                        text = R.string.product_detail_favorite,
                        checked = uiState.isFavorite,
                        updateState = { updateFavorite(it) },
                        isFav = true
                    )

                    Spacer(Modifier.height(32.dp))

                    TitleText(R.string.product_detail_where)
                    EditableText(text = uiState.newWhereTo, { whereToChanged(it) })

                    Spacer(Modifier.height(16.dp))

                    TitleText(R.string.product_detail_info)

                    EditableText(text = uiState.newInfo, { infoChanged(it) }, maxLines = 20)

                    Spacer(Modifier.height(32.dp))

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

                        TextButton(onClick = { saveAndUpdateProduct() }) {
                            Text(
                                stringResource(R.string.product_detail_save),
                                style = MaterialTheme.typography.bodyLarge,
                                color = BlueMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
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
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, bottom = 4.dp)
    )
}

@Composable
fun MainEditableText(
    text: String,
    onValueChange: (String) -> Unit,
    isFav: Boolean
) {
    BasicTextField(
        value = text, onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
            .background(if (isFav) YellowLight else White)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            capitalization = KeyboardCapitalization.Sentences
        ),
    )
}

@Composable
fun EditableText(
    text: String,
    onValueChange: (String) -> Unit,
    maxLines: Int = 1,
) {
    BasicTextField(
        value = text, onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .padding(12.dp),
        textStyle = MaterialTheme.typography.bodyLarge,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            imeAction = if (maxLines == 1) ImeAction.Done else ImeAction.None,
            capitalization = KeyboardCapitalization.Sentences
        ),
    )
}

@Composable
fun SwitchBox(text: Int, checked: Boolean, updateState: (Boolean) -> Unit, isFav: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
            .background(White).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(text),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.SemiBold
        )
        if (isFav) SwitchFavorite(checked, onCheckedChange = { updateState(it) })
        else SwitchHaveIt(checked, onCheckedChange = { updateState(it) })
    }
}