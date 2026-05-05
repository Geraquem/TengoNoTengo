package com.mmfsin.tnt.data.bbdd

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mmfsin.tnt.presentation.utils.SP_ADD_PRODUCT_VISIBLE
import javax.inject.Inject

class SharedPrefs @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun setAddProductVisible(value: Boolean) {
        prefs.edit { putBoolean(SP_ADD_PRODUCT_VISIBLE, value) }
    }

    fun getAddProductVisible(): Boolean {
        return prefs.getBoolean(SP_ADD_PRODUCT_VISIBLE, true)
    }
}