package com.mmfsin.tnt.presentation.home

import com.mmfsin.tnt.domain.models.HomeItem

data class HomeStates(
    val isLoading: Boolean = false,

    val items: List<HomeItem> = emptyList()
)