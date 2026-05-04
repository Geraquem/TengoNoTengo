package com.mmfsin.tnt.presentation.home

import com.mmfsin.tnt.domain.usecases.GetHomeItemsUseCase
import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeItemsUseCase: GetHomeItemsUseCase
) : BaseViewModel<HomeStates>(HomeStates()) {

    fun getHomeItems() {
        executeUseCase(
            {},
            {},
            {}
        )
    }

}