package com.mmfsin.tnt.presentation.myproducts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.overscroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.myproducts.components.AddProduct
import com.mmfsin.tnt.presentation.myproducts.components.ProductFilter
import com.mmfsin.tnt.presentation.myproducts.components.ProductItem
import com.mmfsin.tnt.presentation.utils.closeKeyboard

@Preview
@Composable
fun MyProductsScreenPV() {
    MyProductsContent(
        MyProductsStates(
            //                        products = getExampleProducts()
            products = emptyList()
        ), {}, {}, {}, {}, {})
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
        changeAddProductVisibility = { viewModel.changeAddProductVisibility() }
    )
}

@Composable
fun MyProductsContent(
    uiState: MyProductsStates,
    goBack: () -> Unit,
    onProductToAddChange: (String) -> Unit,
    addProduct: (String) -> Unit,
    updateKeyboardState: () -> Unit,
    changeAddProductVisibility: () -> Unit
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

        val totalElements = (uiState.products.size - 1)

        if (uiState.clearKeyboard) {
            LocalSoftwareKeyboardController.current?.closeKeyboard(LocalFocusManager.current)
            updateKeyboardState()
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
                    ProductFilter()
                    LazyColumn(
                        modifier = Modifier.overscroll(null),
                        contentPadding = PaddingValues(bottom = 150.dp),
                    ) {
                        uiState.products.forEachIndexed { i, product ->
                            item { ProductItem(product, i == totalElements) }
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