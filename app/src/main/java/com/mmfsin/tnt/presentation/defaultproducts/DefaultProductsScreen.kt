package com.mmfsin.tnt.presentation.defaultproducts

import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.DefaultProduct
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.domain.models.getDefaultExamples
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.defaultproducts.components.AddSheet
import com.mmfsin.tnt.presentation.defaultproducts.components.DefaultProductBox

@Preview
@Composable
fun DefaultProductsPV() {
    DefaultProductsContent(
        uiState = DefaultProductsStates(
            products = getDefaultExamples()
        ),
        {}, {}, {}, { }
    )
}

@Composable
fun DefaultProductsScreen(
    viewModel: DefaultProductsViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DefaultProductsContent(
        uiState = uiState,
        goBack = goBack,
        changeAddSheetVisibility = { viewModel.changeAddSheetVisibility() },
        showAddSheet = { defaultProduct -> viewModel.showAddSheet(defaultProduct) },
        addProduct = { p -> viewModel.addProduct(p) }
    )
}

@Composable
fun DefaultProductsContent(
    uiState: DefaultProductsStates,
    goBack: () -> Unit,
    changeAddSheetVisibility: () -> Unit,
    showAddSheet: (DefaultProduct) -> Unit,
    addProduct: (Product) -> Unit
) {
    Scaffold(
        topBar = { Toolbar(text = R.string.df_products_title, onBackClick = { goBack() }) }
    ) { innerPadding ->
        Box(
            Modifier.fillMaxSize()
                .background(GrayLight)
                .padding(innerPadding)
        ) {
            CompositionLocalProvider(LocalOverscrollFactory provides null) {
                LazyVerticalGrid(
                    contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.products) { product ->
                        DefaultProductBox(
                            defaultProduct = product,
                            onClick = { showAddSheet(product) }
                        )
                    }
                }
            }
        }

        if (uiState.addSheetVisibility) {
            uiState.defaultProductSelected?.let { dP ->
                AddSheet(
                    onDismiss = { changeAddSheetVisibility() },
                    defaultProduct = dP,
                    addProduct = { p -> addProduct(p) }
                )
            }
        }
    }
}