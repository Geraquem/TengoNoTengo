package com.mmfsin.tnt.presentation.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight

@Preview
@Composable
fun ProductDetailPV() {
    ProductDetailContent(
        uiState = ProductDetailStates(),
        {},
    )
}

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    goBack: () -> Unit,
    productId: String,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductDetailContent(
        uiState = uiState,
        goBack = goBack
    )
}

@Composable
fun ProductDetailContent(
    uiState: ProductDetailStates,
    goBack: () -> Unit
) {
    Scaffold(
        topBar = { Toolbar(iconVisible = true, onBackClick = { goBack() }) }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).background(GrayLight))
    }
}