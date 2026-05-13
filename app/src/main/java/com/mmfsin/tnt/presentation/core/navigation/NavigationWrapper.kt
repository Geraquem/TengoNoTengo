package com.mmfsin.tnt.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.tnt.domain.models.HomeTypeClassification.BY_CATEGORIES
import com.mmfsin.tnt.domain.models.HomeTypeClassification.DONT_HAVE
import com.mmfsin.tnt.domain.models.HomeTypeClassification.FAVORITES
import com.mmfsin.tnt.domain.models.HomeTypeClassification.HAVE
import com.mmfsin.tnt.domain.models.HomeTypeClassification.MY_PRODUCTS
import com.mmfsin.tnt.presentation.createProduct.CreateAdvancedProductScreen
import com.mmfsin.tnt.presentation.donthave.DontHaveScreen
import com.mmfsin.tnt.presentation.home.HomeScreen
import com.mmfsin.tnt.presentation.myproducts.MyProductsScreen
import com.mmfsin.tnt.presentation.productdetail.ProductDetailScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        composable<Home> {
            HomeScreen(
                navigateTo = { type ->
                    when (type) {
                        MY_PRODUCTS -> navController.navigate(MyProducts)
                        DONT_HAVE -> navController.navigate(ProductsIDontHave)
                        HAVE -> {}
                        FAVORITES -> {}
                        BY_CATEGORIES -> {}
                    }
                }
            )
        }

        composable<MyProducts> {
            MyProductsScreen(
                goBack = { navController.popBackStack() },
                toProductDetail = { id -> navController.navigate(ProductDetail(id)) },
                toCreateAdvancedProduct = { name -> navController.navigate(CreateAdvancedProduct(name)) }
            )
        }

        composable<ProductDetail> { data ->
            /** internamente procesa data y se lo pasa al viewmodel */
            ProductDetailScreen(
                goBack = { navController.popBackStack() },
            )
        }

        composable<CreateAdvancedProduct> { data ->
            /** internamente procesa data y se lo pasa al viewmodel */
            CreateAdvancedProductScreen(
                goBack = { navController.popBackStack() },
            )
        }

        composable<ProductsIDontHave> {
            DontHaveScreen(goBack = { navController.popBackStack() })
        }
    }
}