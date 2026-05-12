@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.tnt.presentation.productdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mmfsin.tnt.domain.models.Category
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getCategories
import com.mmfsin.tnt.presentation.core.components.MyRadioButton
import com.mmfsin.tnt.presentation.core.theme.GreenLight
import com.mmfsin.tnt.presentation.core.theme.White

@Preview
@Composable
fun CategoryDialogPV() {
    CategoryDialog(
        visible = true,
        categories = getCategories(),
        selectedId = 2,
        {}, {})
}

@Composable
fun CategoryDialog(
    visible: Boolean,
    categories: List<Category>,
    selectedId: Int,
    onDismiss: () -> Unit,
    onCategorySelected: (Int) -> Unit,
) {

    val listState = rememberLazyListState()

    LaunchedEffect(selectedId) {
        val index = categories.indexOfFirst { it.id == selectedId }
        if (index != -1) {
            listState.animateScrollToItem(index)
        }
    }

    if (visible) {
        Dialog(
            onDismissRequest = { onDismiss() },
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth().height(500.dp).clip(RoundedCornerShape(16.dp))
            ) {
                items(categories) { item ->
                    CategoryItem(
                        category = item,
                        selectedId = selectedId,
                        onClick = { id -> onCategorySelected(id) }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    selectedId: Int,
    onClick: (Int) -> Unit
) {
    val selected = category.id == selectedId
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(if (!selected) White else GreenLight)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable(onClick = { onClick(category.id) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        category.icon?.let { icon ->
            Icon(
                painter = painterResource(icon), stringResource(category.name),
                tint = category.color,
                modifier = Modifier.size(28.dp),
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(category.name),
            style = MaterialTheme.typography.bodyLarge
        )
        MyRadioButton(selected = selected, onClick = { onClick(category.id) })
    }
}