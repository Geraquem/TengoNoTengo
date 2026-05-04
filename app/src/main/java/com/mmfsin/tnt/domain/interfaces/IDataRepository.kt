package com.mmfsin.tnt.domain.interfaces

import com.mmfsin.tnt.domain.models.HomeItem

interface IDataRepository {
    fun getHomeItems(): List<HomeItem>
}