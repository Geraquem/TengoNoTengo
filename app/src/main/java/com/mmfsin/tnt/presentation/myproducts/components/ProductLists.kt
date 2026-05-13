package com.mmfsin.tnt.presentation.myproducts.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.Category
import com.mmfsin.tnt.domain.models.Product
import com.mmfsin.tnt.domain.models.getExampleProducts
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.core.theme.RedHard

@Preview
@Composable
fun ProductListPV() {
    Column() {
        ProductListByCategories(
            getExampleProducts(),
            { _, _ -> }, {}
        )
        Spacer(Modifier.fillMaxWidth().height(24.dp).background(RedHard))
        ProductList(
            getExampleProducts(),
            { _, _ -> }, {})
    }
}

@Composable
fun ProductListByCategories(
    products: List<Product>,
    updateHaveItProduct: (String, Boolean) -> Unit,
    toProductDetail: (String) -> Unit
) {

    val categoriesExpanded = remember { mutableStateMapOf<Int, Boolean>() }

    LazyColumn(contentPadding = PaddingValues(bottom = 150.dp)) {
        var lastCategoryId: Int? = null

        items(products) { product ->
            val categoryId = product.category.id

            if (categoriesExpanded[categoryId] == null) {
                categoriesExpanded[categoryId] = true
            }

            if (lastCategoryId != categoryId) {
                lastCategoryId = categoryId
                CategoryHeader(
                    category = product.category,
                    expanded = categoriesExpanded[categoryId] == true,
                    productsVisibility = {
                        categoriesExpanded[categoryId] = !(categoriesExpanded[categoryId] ?: true)
                    })
            }

            if (categoriesExpanded[categoryId] == true) {
                ProductItem(
                    product = product,
                    updateHaveIt = { id, haveIt -> updateHaveItProduct(id, haveIt) },
                    onProductClick = { id -> toProductDetail(id) },
                    categoryIconVisible = false
                )
            }
        }
    }
}

@Composable
fun CategoryHeader(
    category: Category,
    expanded: Boolean,
    productsVisibility: (Int) -> Unit
) {

    val openedDropMenu by animateFloatAsState(
        targetValue = if (expanded) 1f else -1f,
        label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(GrayLight)
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        category.icon?.let { icon ->
            Icon(
                painterResource(icon), stringResource(category.name),
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Text(
            text = stringResource(category.name),
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(Modifier.weight(1f))

        IconButton(onClick = { productsVisibility(category.id) }) {
            Icon(
                painterResource(R.drawable.ic_arrow_down), stringResource(category.name),
                modifier = Modifier.graphicsLayer { scaleY = openedDropMenu }
            )
        }
    }
}

@Composable
fun ProductList(
    products: List<Product>,
    updateHaveItProduct: (String, Boolean) -> Unit,
    toProductDetail: (String) -> Unit
) {
    LazyColumn(contentPadding = PaddingValues(bottom = 150.dp)) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductItem(
                product = product,
                updateHaveIt = { id, haveIt -> updateHaveItProduct(id, haveIt) },
                onProductClick = { id -> toProductDetail(id) },
                categoryIconVisible = true
            )
        }
    }
}