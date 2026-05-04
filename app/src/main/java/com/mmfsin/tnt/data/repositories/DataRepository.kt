package com.mmfsin.tnt.data.repositories

import com.mmfsin.tnt.domain.interfaces.IDataRepository
import com.mmfsin.tnt.domain.models.HomeItem
import javax.inject.Inject

class DataRepository @Inject constructor() : IDataRepository {
    override fun getHomeItems(): List<HomeItem> {
        return emptyList()
    }
}