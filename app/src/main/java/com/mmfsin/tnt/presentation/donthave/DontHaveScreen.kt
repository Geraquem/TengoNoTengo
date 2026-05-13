package com.mmfsin.tnt.presentation.donthave

import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.getExampleProducts
import com.mmfsin.tnt.presentation.core.components.LoadingFullScreen
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.myproducts.components.ProductItem

@Preview
@Composable
fun DontHaveScreenPV() {
    DontHaveContent(
        uiState = DontHaveStates(
            isLoading = false,
            products = getExampleProducts()
        ),
        {}
    )
}

@Composable
fun DontHaveScreen(viewModel: DontHaveViewModel = hiltViewModel(), goBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DontHaveContent(
        uiState = uiState,
        goBack = goBack
    )
}

@Composable
fun DontHaveContent(
    uiState: DontHaveStates,
    goBack: () -> Unit
) {
    Scaffold(
        topBar = { Toolbar(text = R.string.classification_dont_have_title, onBackClick = { goBack() }) }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().background(GrayLight).padding(innerPadding)) {
            if (uiState.products.isEmpty()) {
                Text(
                    stringResource(R.string.classification_dont_have_empty),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center).alpha(0.75f)
                )
            } else {
                CompositionLocalProvider(LocalOverscrollFactory provides null) {
                    LazyColumn(contentPadding = PaddingValues(bottom = 150.dp)) {
                        items(
                            items = uiState.products,
                            key = { it.id }
                        ) { product ->
                            ProductItem(
                                product = product,
                                updateHaveIt = { id, haveIt -> /*updateHaveItProduct(id, haveIt)*/ },
                                onProductClick = { id -> /*toProductDetail(id)*/ }
                            )
                        }
                    }
                }
            }
        }
        if (uiState.isLoading) LoadingFullScreen()
    }
}