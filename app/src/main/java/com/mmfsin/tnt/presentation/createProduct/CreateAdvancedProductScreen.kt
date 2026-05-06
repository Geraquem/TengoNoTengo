package com.mmfsin.tnt.presentation.createProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
fun CreateAdvancedProductScreenPV() {
    CreateAdvancedProductContent(
        uiState = CreateAdvancedProductStates(
            name = "Soja texturizada"
        ),
        {}
    )
}

@Composable
fun CreateAdvancedProductScreen(viewModel: CreateAdvancedProductViewModel = hiltViewModel(), goBack: () -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CreateAdvancedProductContent(
        uiState = uiState,
        goBack = goBack,
    )
}

@Composable
fun CreateAdvancedProductContent(
    uiState: CreateAdvancedProductStates,
    goBack: () -> Unit,
) {
    Scaffold(
        topBar = { Toolbar(onBackClick = goBack) }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding).background(GrayLight)) {
            Text(text = uiState.name)
        }
    }
}