package com.mmfsin.tnt.presentation.createProduct

import androidx.lifecycle.SavedStateHandle
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateAdvancedProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<CreateAdvancedProductStates>(CreateAdvancedProductStates()) {

    private val productName: String? = savedStateHandle["name"]

    init {
        updateName(productName ?: "")
    }

    fun updateName(value: String) = _uiState.update { it.copy(name = value) }
}