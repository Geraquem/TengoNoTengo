package com.mmfsin.tnt.presentation.home

import com.mmfsin.tnt.domain.usecases.GetHomeItemsUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeItemsUseCase: GetHomeItemsUseCase
) : BaseViewModel<HomeStates>(HomeStates()) {

    init {
        getHomeItems()
    }

    fun getHomeItems() {
        executeUseCase(
            { getHomeItemsUseCase() },
            { items ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        items = items
                    )
                }
            },
            {}
        )
    }

}