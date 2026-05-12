package com.mmfsin.tnt.presentation.myproducts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.FilterType.Companion.getAllFilters
import com.mmfsin.tnt.presentation.core.theme.White

@Preview
@Composable
fun FilterDialogPV() {
    FilterDialog(0, {}, {})
}

@Composable
fun FilterDialog(
    actualFilterId: Int,
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var selected by remember { mutableIntStateOf(actualFilterId) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(White)
                .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.filter_dialog_title),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            val filters = getAllFilters()
            filters.forEach { f ->
                RadioButtonComponent(
                    type = f,
                    selectedId = selected,
                    select = { id ->
                        selected = id
                        onConfirm(id)
                    }
                )
            }
        }
    }
}

@Composable
fun RadioButtonComponent(type: FilterType, selectedId: Int, select: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = { select(type.id) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = type.id == selectedId, onClick = { select(type.id) })
        Text(stringResource(type.text), style = MaterialTheme.typography.bodyLarge)
    }
}