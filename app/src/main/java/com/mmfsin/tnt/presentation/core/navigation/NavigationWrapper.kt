package com.mmfsin.tnt.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.tnt.domain.models.HomeType
import com.mmfsin.tnt.presentation.home.HomeScreen
import com.mmfsin.tnt.presentation.myproducts.MyProductsScreen

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
                goBack = { navController.popBackStack() }
            )
        }
    }
}