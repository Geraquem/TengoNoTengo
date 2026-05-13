package com.mmfsin.tnt.presentation.donthave

import androidx.lifecycle.viewModelScope
import com.mmfsin.tnt.domain.models.FilterType.ALPHABETIC
import com.mmfsin.tnt.domain.usecases.GetActualFilterUseCase
import com.mmfsin.tnt.domain.usecases.GetProductsByHaveItUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import com.mmfsin.tnt.presentation.utils.sortedByFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DontHaveViewModel @Inject constructor(
    private val getProductsByHaveItUseCase: GetProductsByHaveItUseCase,
    private val getActualFilterUseCase: GetActualFilterUseCase
) : BaseViewModel<DontHaveStates>(DontHaveStates()) {

    private val filterFlow = MutableStateFlow(ALPHABETIC)

    init {
        showLoading()
        observeDontHaveProducts()
        getActualFilter()
    }

    private fun showLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun observeDontHaveProducts() {
        viewModelScope.launch {
            combine(
                getProductsByHaveItUseCase(haveIt = false),
                filterFlow
            ) { products, filter ->
                products.sortedByFilter(filter)
            }.collect { sortedProducts ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        products = sortedProducts
                    )
                }
            }
        }
    }

    fun getActualFilter() {
        executeUseCase(
            { getActualFilterUseCase() },
            { actualFilter ->
                filterFlow.value = actualFilter
                _uiState.update { it.copy(actualFilter = actualFilter) }
            },
            {}
        )
    }
}