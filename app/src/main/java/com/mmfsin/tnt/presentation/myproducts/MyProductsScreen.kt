package com.mmfsin.tnt.presentation.myproducts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.myproducts.components.AddProduct

@Preview
@Composable
fun MyProductsScreenPV() {
    MyProductsContent(MyProductsStates(), {}, {})
}

@Composable
fun MyProductsScreen(viewModel: MyProductsViewModel = hiltViewModel(), goBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MyProductsContent(
        uiState = uiState,
        goBack = goBack,
        onProductToAddChange = { viewModel.onProductToAddChange(it) }
    )
}

@Composable
fun MyProductsContent(
    uiState: MyProductsStates,
    goBack: () -> Unit,
    onProductToAddChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            Toolbar(
                text = R.string.my_products_toolbar,
                iconVisible = true
            ) { goBack() }
        }
    ) { innerPadding ->
        Column(Modifier.fillMaxSize().background(GrayLight).padding(innerPadding)) {
            Spacer(Modifier.weight(1f))
            AddProduct(uiState.productToAdd, onValueChange = { onProductToAddChange(it) })
        }
    }
}