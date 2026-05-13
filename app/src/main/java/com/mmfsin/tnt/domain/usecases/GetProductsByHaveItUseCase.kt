package com.mmfsin.tnt.domain.usecases

import com.mmfsin.tnt.domain.interfaces.IDataRepository
import com.mmfsin.tnt.domain.models.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByHaveItUseCase @Inject constructor(
    private val repository: IDataRepository,
) {
    operator fun invoke(haveIt: Boolean): Flow<List<Product>> =
        if (haveIt) repository.getProductsIHave()
        else repository.getProductsIDontHave()

}