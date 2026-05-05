package com.mmfsin.tnt.domain.models

import com.mmfsin.tnt.R

enum class FilterType(val id: Int, val text: Int) {
    ALPHABETIC(0, R.string.filter_dialog_alphabet),
    FAVORITES_FIRST(1, R.string.filter_dialog_favs_first),
    DONT_HAVE_FIRST(2, R.string.filter_dialog_dont_have_first),
    HAVE_FIRST(3, R.string.filter_dialog_i_have_first),
    LAST_ADDED_FIRST(4, R.string.filter_dialog_last_first);

    companion object {
        fun getFilterById(id: Int): FilterType {
            return entries.firstOrNull { it.id == id } ?: ALPHABETIC
        }
    }
}

