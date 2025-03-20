package com.example.pokai.pokaichao_comp304sec001_lab03

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokai.pokaichao_comp304sec001_lab03.ui.theme.HomeScreen
import com.example.pokai.pokaichao_comp304sec001_lab03.ui.theme.ProductScreen
import com.example.pokai.pokaichao_comp304sec001_lab03.viewmodel.ProductViewModel

//All screens
enum class Screen(@StringRes val title: Int) {
    Home(title = R.string.screen_home),
    Product(title = R.string.screen_product)
}

@Composable
fun ProductApp(
    productViewModel: ProductViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            MyAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.name) {
                HomeScreen(
                    products = productViewModel.products,
                    onFabClick = { navController.navigate(Screen.Product.name) },
                    onProductClick = { product ->
                        productViewModel.updateProduct(product)
                        navController.navigate(Screen.Product.name)
                    }
                )
            }

            composable(route = Screen.Product.name) {
                ProductScreen(
                    productViewModel = productViewModel,
                    onAddClick = { product ->
                        productViewModel.addProduct(product)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_topBar)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.button_back)
                    )
                }
            }
        })
}