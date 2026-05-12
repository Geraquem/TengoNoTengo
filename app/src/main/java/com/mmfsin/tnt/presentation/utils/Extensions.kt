package com.mmfsin.tnt.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.mmfsin.tnt.domain.models.FilterType
import com.mmfsin.tnt.domain.models.Product

fun SoftwareKeyboardController.closeKeyboard(focusManager: FocusManager) {
    this.hide()
    focusManager.clearFocus()
}

fun List<Product>.sortedByFilter(filter: FilterType): List<Product> {
    return when (filter) {
        FilterType.ALPHABETIC -> this.sortedBy { it.name.lowercase() }

        FilterType.FAVORITES_FIRST -> {
            this.sortedWith(
                compareByDescending<Product> { it.favorite }
                    .thenBy { it.name.lowercase() }
            )
        }

        FilterType.DONT_HAVE_FIRST -> {
            this.sortedWith(
                compareBy<Product> { it.haveIt }
                    .thenBy { it.name.lowercase() }
            )
        }

        FilterType.HAVE_FIRST -> {
            this.sortedWith(
                compareByDescending<Product> { it.haveIt }
                    .thenBy { it.name.lowercase() }
            )
        }

        FilterType.LAST_ADDED_FIRST -> this.sortedByDescending { it.name }

        FilterType.BY_CATEGORY -> {
            this.sortedWith(
                compareBy<Product> { it.category.id }
                    .thenBy { it.name.lowercase() }
            )
        }
    }
}

@Composable
fun middleBoldText(strRef: Int, middleText: String): AnnotatedString {
    return buildAnnotatedString {
        append(stringResource(strRef).substringBefore("%s"))
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append(middleText) }
        append(stringResource(strRef).substringAfter("%s"))
    }
}