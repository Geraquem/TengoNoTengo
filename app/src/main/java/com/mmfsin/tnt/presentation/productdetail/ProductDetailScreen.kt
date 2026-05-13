package com.mmfsin.tnt.presentation.productdetail

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
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getCategories
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getCategoryById
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.productdetail.components.DeleteProductDialog
import com.mmfsin.tnt.presentation.productdetail.components.ProductDetailData

@Preview
@Composable
fun ProductDetailPV() {
    ProductDetailContent(
        uiState = ProductDetailStates(
            oldProduct = Product(
                id = "",
                name = "",
                info = "",
                whereToFind = "",
                haveIt = false,
                favorite = true,
                category = getCategoryById(6),
                date = 0
            ),
            newName = "Soja texturizada",
            newFavorite = false,
            newHaveIt = true,
            deleteDialog = false,
            categories = getCategories(),
            categoriesState = false
        ),
        {}, {}, {}, {}, {}, {}, {},
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
        onNameChange = { viewModel.onNameChanged(it) },
        onWhereToChanged = { viewModel.onWhereToFindChanged(it) },
        onInfoChanged = { viewModel.onInfoChanged(it) },
        updateHaveIt = { viewModel.updateHaveIt(it) },
        updateFavorite = { viewModel.updateFavorite(it) },
        updateCategory = { viewModel.updateCategory(it) },
        updateCategoriesState = { viewModel.updateCategoriesState() },
        updateDeleteDialogVisibility = { viewModel.updateDeleteDialogVisibility() },
        deleteProduct = { viewModel.deleteProduct() },
        saveAndUpdateProduct = { viewModel.saveAndUpdateProduct() }
    )
}

@Composable
fun ProductDetailContent(
    uiState: ProductDetailStates,
    goBack: () -> Unit,
    onNameChange: (String) -> Unit,
    onWhereToChanged: (String) -> Unit,
    onInfoChanged: (String) -> Unit,
    updateHaveIt: (Boolean) -> Unit,
    updateFavorite: (Boolean) -> Unit,
    updateCategory: (Int) -> Unit,
    updateCategoriesState: () -> Unit,
    updateDeleteDialogVisibility: () -> Unit,
    deleteProduct: () -> Unit,
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

            ProductDetailData(
                name = uiState.newName,
                onNameChanged = { onNameChange(it) },
                whereToFind = uiState.newWhereToFind,
                onWhereToFindChanged = { onWhereToChanged(it) },
                info = uiState.newInfo,
                onInfoChanged = { onInfoChanged(it) },
                haveIt = uiState.newHaveIt,
                updateHaveIt = { updateHaveIt(it) },
                favorite = uiState.newFavorite,
                updateFavorite = { updateFavorite(it) },
                categories = uiState.categories,
                category = uiState.newCategory,
                updateCategory = { updateCategory(it) },
                categoriesState = uiState.categoriesState,
                updateCategoriesState = { updateCategoriesState() },
                deleteButtonVisible = true,
                onDeleteClick = { updateDeleteDialogVisibility() },
                onConfirmClick = { saveAndUpdateProduct() }
            )
        }
    }
}