package com.mmfsin.tnt.presentation.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.tnt.presentation.createproduct.CreateAdvancedProductScreen
import com.mmfsin.tnt.presentation.defaultproducts.DefaultProductsScreen
import com.mmfsin.tnt.presentation.home.HomeScreen
import com.mmfsin.tnt.presentation.myproducts.MyProductsScreen
import com.mmfsin.tnt.presentation.productdetail.ProductDetailScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {

        composable<Home> {
            HomeScreen(
                navigateToHomeClassification = { typeId ->
                    navController.navigate(MyProducts(typeId))
                },
                navigateToDefaultProducts = { navController.navigate(DefaultProducts) }
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

        composable<DefaultProducts> {
            DefaultProductsScreen(
                goBack = { navController.popBackStack() }
            )
        }
    }
}