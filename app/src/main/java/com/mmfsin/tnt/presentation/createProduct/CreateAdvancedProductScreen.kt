package com.mmfsin.tnt.presentation.createProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.domain.models.createEmptyProduct
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.productdetail.components.ProductDetailData

@Preview
@Composable
fun CreateAdvancedProductScreenPV() {
    CreateAdvancedProductContent(
        uiState = CreateAdvancedProductStates(
            product = createEmptyProduct().copy(name = "Soja texturizada")
        ),
        {}, {}, {}, {},
        {}, {}, {},
        {}, {},
    )
}

@Composable
fun CreateAdvancedProductScreen(viewModel: CreateAdvancedProductViewModel = hiltViewModel(), goBack: () -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CreateAdvancedProductContent(
        uiState = uiState,
        goBack = goBack,
        onNameChange = { viewModel.onNameChanged(it) },
        onWhereToChanged = { viewModel.onWhereToFindChanged(it) },
        onInfoChanged = { viewModel.onInfoChanged(it) },
        updateHaveIt = { viewModel.updateHaveIt(it) },
        updateFavorite = { viewModel.updateFavorite(it) },
        updateCategoriesState = { viewModel.updateCategoriesState() },
        updateCategory = { viewModel.updateCategory(it) },
        createProduct = { viewModel.createProduct() }
    )
}

@Composable
fun CreateAdvancedProductContent(
    uiState: CreateAdvancedProductStates,
    goBack: () -> Unit,
    onNameChange: (String) -> Unit,
    onWhereToChanged: (String) -> Unit,
    onInfoChanged: (String) -> Unit,
    updateHaveIt: (Boolean) -> Unit,
    updateFavorite: (Boolean) -> Unit,
    updateCategoriesState: () -> Unit,
    updateCategory: (Int) -> Unit,
    createProduct: () -> Unit
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

            ProductDetailData(
                name = uiState.product.name,
                onNameChanged = { onNameChange(it) },
                whereToFind = uiState.product.whereToFind,
                onWhereToFindChanged = { onWhereToChanged(it) },
                info = uiState.product.info,
                onInfoChanged = { onInfoChanged(it) },
                haveIt = uiState.product.haveIt,
                updateHaveIt = { updateHaveIt(it) },
                favorite = uiState.product.favorite,
                updateFavorite = { updateFavorite(it) },
                categories = uiState.categories,
                category = uiState.product.category,
                updateCategory = { updateCategory(it) },
                categoriesState = uiState.categoriesState,
                updateCategoriesState = { updateCategoriesState() },
                deleteButtonVisible = false,
                onConfirmClick = { createProduct() }
            )
        }
    }
}