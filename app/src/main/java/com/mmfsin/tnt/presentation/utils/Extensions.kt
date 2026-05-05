package com.mmfsin.tnt.presentation.utils

import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.mmfsin.tnt.data.ProductDTO
import com.mmfsin.tnt.domain.models.FilterType

fun SoftwareKeyboardController.closeKeyboard(focusManager: FocusManager) {
    this.hide()
    focusManager.clearFocus()
}

fun List<ProductDTO>.sortedByFilter(filter: FilterType): List<ProductDTO> {
    return when (filter) {
        FilterType.ALPHABETIC -> this.sortedBy { it.name.lowercase() }

        FilterType.FAVORITES_FIRST -> {
            this.sortedWith(
                compareByDescending<ProductDTO> { it.favorite }
                    .thenBy { it.name.lowercase() }
            )
        }

        FilterType.DONT_HAVE_FIRST -> {
            this
        }

        FilterType.HAVE_FIRST -> {
            this
        }

        FilterType.LAST_ADDED_FIRST -> this.sortedByDescending { it.date }
    }
}