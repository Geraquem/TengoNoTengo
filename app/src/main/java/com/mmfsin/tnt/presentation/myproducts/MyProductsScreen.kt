@file:OptIn(ExperimentalFoundationApi::class)

package com.mmfsin.tnt.presentation.myproducts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.getExampleProducts
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.myproducts.components.AddProduct
import com.mmfsin.tnt.presentation.myproducts.components.FilterDialog
import com.mmfsin.tnt.presentation.myproducts.components.ProductFilter
import com.mmfsin.tnt.presentation.myproducts.components.ProductItem
import com.mmfsin.tnt.presentation.utils.closeKeyboard

@Preview
@Composable
fun MyProductsScreenPV() {
    MyProductsContent(
        MyProductsStates(
            products = getExampleProducts(),
            //            products = emptyList(),
            showFilterDialog = false,
            actualFilter = FilterType.DONT_HAVE_FIRST
        ), {}, {}, {}, {},
        {}, {}, {}, { _, _ -> })
}

@Composable
fun MyProductsScreen(viewModel: MyProductsViewModel = hiltViewModel(), goBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MyProductsContent(
        uiState = uiState,
        goBack = goBack,
        onProductToAddChange = { viewModel.onProductToAddChange(it) },
        addProduct = { viewModel.addSingleProduct(name = it) },
        updateKeyboardState = { viewModel.updateClearKeyboard() },
        changeAddProductVisibility = { viewModel.changeAddProductVisibility() },
        showFilterDialog = { viewModel.updateFilterDialogVisibility() },
        updateFilterType = { id -> viewModel.updateFilterType(id) },
        updateHaveItProduct = { id, haveIt -> viewModel.updateHaveProduct(id, haveIt) }
    )
}

@Composable
fun MyProductsContent(
    uiState: MyProductsStates,
    goBack: () -> Unit,
    onProductToAddChange: (String) -> Unit,
    addProduct: (String) -> Unit,
    updateKeyboardState: () -> Unit,
    changeAddProductVisibility: () -> Unit,
    showFilterDialog: () -> Unit,
    updateFilterType: (Int) -> Unit,
    updateHaveItProduct: (String, Boolean) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            Toolbar(
                text = R.string.my_products_toolbar,
                iconVisible = true
            ) { goBack() }
        }
    ) { innerPadding ->

        if (uiState.clearKeyboard) {
            LocalSoftwareKeyboardController.current?.closeKeyboard(LocalFocusManager.current)
            updateKeyboardState()
        }

        if (uiState.showFilterDialog) {
            FilterDialog(
                actualFilterId = uiState.actualFilter?.id ?: FilterType.ALPHABETIC.id,
                onConfirm = { id -> updateFilterType(id) },
                onDismiss = { showFilterDialog() }
            )
        }

        Box(Modifier.fillMaxSize().background(GrayLight).padding(innerPadding)) {
            if (uiState.products.isEmpty()) {
                Text(
                    stringResource(R.string.my_products_nothing_added),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center).alpha(0.75f)
                )
            } else {
                Column {
                    uiState.actualFilter?.let { filterType -> ProductFilter(filterType.text) { showFilterDialog() } }

                    CompositionLocalProvider(LocalOverscrollFactory provides null) {
                        LazyColumn(contentPadding = PaddingValues(bottom = 150.dp)) {
                            itemsIndexed(
                                items = uiState.products,
                                key = { _, p -> p.id }
                            ) { i, product ->
                                val lastIndex = uiState.products.lastIndex
                                ProductItem(
                                    product = product,
                                    isLast = i == lastIndex,
                                    updateHaveIt = { id, haveIt -> updateHaveItProduct(id, haveIt) }
                                )
                            }
                        }
                    }
                }
            }
            AddProduct(
                isVisible = uiState.productToAddVisible,
                product = uiState.productToAdd, onValueChange = { onProductToAddChange(it) },
                addProduct = { addProduct(it) },
                advancedMode = {},
                changeVisibility = { changeAddProductVisibility() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .imePadding()
            )
        }
    }
}