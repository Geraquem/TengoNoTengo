package com.mmfsin.tnt.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.tnt.domain.models.HomeType
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
                        HomeType.MY_PRODUCTS -> navController.navigate(MyProducts)
                        else -> {}
                    }
                }
            )
        }

        composable<MyProducts> {
            MyProductsScreen(
                goBack = { navController.popBackStack() },
                toProductDetail = { id -> navController.navigate(ProductDetail(id)) },
                toCreateAdvancedProduct = {}
            )
        }

        composable<ProductDetail> { data ->
            /** internamente procesa data y se lo pasa al viewmodel */
            ProductDetailScreen(
                goBack = { navController.popBackStack() },
            )
        }
    }
}