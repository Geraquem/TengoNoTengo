package com.mmfsin.tnt.presentation.productdetail

import com.mmfsin.tnt.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(

) : BaseViewModel<ProductDetailStates>(ProductDetailStates()) {


}