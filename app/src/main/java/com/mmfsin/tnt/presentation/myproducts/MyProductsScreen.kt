@file:OptIn(ExperimentalFoundationApi::class)

package com.mmfsin.tnt.presentation.myproducts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.presentation.core.components.LoadingFullScreen
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.myproducts.components.AddProduct
import com.mmfsin.tnt.presentation.myproducts.components.EmptyScreen
import com.mmfsin.tnt.presentation.myproducts.components.FilterDialog
import com.mmfsin.tnt.presentation.myproducts.components.ProductList
import com.mmfsin.tnt.presentation.myproducts.components.ProductListByCategories

@Preview
@Composable
fun MyProductsScreenPV() {
    MyProductsContent(
        MyProductsStates(
            isLoading = false,
            //            products = getExampleProducts(),
            products = emptyList(),
            byCategories = true,
            filterDialogVisible = false,
            actualFilter = FilterType.DONT_HAVE_FIRST
        ), {}, {}, {}, {}, {},
        {}, {}, {}, { _, _ -> },
        { }
    )
}

@Composable
fun MyProductsScreen(
    viewModel: MyProductsViewModel = hiltViewModel(),
    goBack: () -> Unit,
    toCreateAdvancedProduct: (String) -> Unit,
    toProductDetail: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MyProductsContent(
        uiState = uiState,
        goBack = goBack,
        onProductToAddChange = { viewModel.onProductToAddChange(it) },
        createAdvancedProduct = { name -> toCreateAdvancedProduct(name) },
        addProduct = { viewModel.addSingleProduct(name = it) },
        updateKeyboardState = { viewModel.updateClearKeyboard() },
        changeAddProductVisibility = { viewModel.changeAddProductVisibility() },
        updateFilterDialogVisibility = { viewModel.updateFilterDialogVisibility() },
        updateFilterType = { id -> viewModel.updateFilterType(id) },
        updateHaveItProduct = { id, haveIt -> viewModel.updateHaveProduct(id, haveIt) },
        toProductDetail = { id -> toProductDetail(id) }
    )
}

@Composable
fun MyProductsContent(
    uiState: MyProductsStates,
    goBack: () -> Unit,
    onProductToAddChange: (String) -> Unit,
    createAdvancedProduct: (String) -> Unit,
    addProduct: (String) -> Unit,
    updateKeyboardState: () -> Unit,
    changeAddProductVisibility: () -> Unit,
    updateFilterDialogVisibility: () -> Unit,
    updateFilterType: (Int) -> Unit,
    updateHaveItProduct: (String, Boolean) -> Unit,
    toProductDetail: (String) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            Toolbar(
                text = uiState.title,
                onBackClick = { goBack() },
                rightIcon = if (uiState.products.isNotEmpty() && !uiState.byCategories) R.drawable.ic_sort else null,
                onRightIconClick = { updateFilterDialogVisibility() }
            )
        }
    ) { innerPadding ->

        if (uiState.clearKeyboard) {
            /** Mejor si no se oculta el teclado */
            //            LocalSoftwareKeyboardController.current?.closeKeyboard(LocalFocusManager.current)
            updateKeyboardState()
        }

        if (uiState.filterDialogVisible) {
            FilterDialog(
                actualFilterId = uiState.actualFilter?.id ?: FilterType.ALPHABETIC.id,
                onConfirm = { id -> updateFilterType(id) },
                onDismiss = { updateFilterDialogVisibility() }
            )
        }

        Box(Modifier.fillMaxSize().background(GrayLight).padding(innerPadding)) {
            if (uiState.products.isEmpty()) {
                EmptyScreen(
                    text = uiState.emptyMessage,
                )
            } else {
                CompositionLocalProvider(LocalOverscrollFactory provides null) {
                    if (uiState.byCategories) {
                        ProductListByCategories(
                            products = uiState.products,
                            updateHaveItProduct = { id, haveIt -> updateHaveItProduct(id, haveIt) },
                            toProductDetail = { id -> toProductDetail(id) }
                        )

                    } else {
                        ProductList(
                            products = uiState.products,
                            updateHaveItProduct = { id, haveIt -> updateHaveItProduct(id, haveIt) },
                            toProductDetail = { id -> toProductDetail(id) }
                        )
                    }
                }
            }
            AddProduct(
                isVisible = uiState.productToAddVisible,
                product = uiState.productToAdd, onValueChange = { onProductToAddChange(it) },
                addProduct = { addProduct(it) },
                advancedMode = { name ->
                    onProductToAddChange("")
                    createAdvancedProduct(name)
                },
                changeVisibility = { changeAddProductVisibility() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .imePadding()
            )
        }

        if (uiState.isLoading) LoadingFullScreen()
    }
}