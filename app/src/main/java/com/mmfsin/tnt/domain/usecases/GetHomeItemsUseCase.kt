package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.models.HomeItem
import com.mmfsin.tnt.domain.models.HomeTypeClassification.Companion.getHomeItems
import javax.inject.Inject

class GetHomeItemsUseCase @Inject constructor() {
    operator fun invoke(): List<HomeItem> = getHomeItems()
}
