package com.bsuir.tserashkevich.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bsuir.tserashkevich.screens.AboutScreen
import com.bsuir.tserashkevich.screens.AddScreen
import com.bsuir.tserashkevich.screens.EditScreen
import com.bsuir.tserashkevich.screens.FavoriteScreen
import com.bsuir.tserashkevich.screens.HomeScreen
import com.bsuir.tserashkevich.viewModels.FilmViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun Navigation(navController: NavController, coroutineScope: CoroutineScope, snackbarHostState : SnackbarHostState){
    val viewModel = viewModel<FilmViewModel>()
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.AboutScreen.route) {
            AboutScreen()
        }
        composable(route = Screens.FavoriteScreen.route) {
            FavoriteScreen()
        }
        composable(route = Screens.AddScreen.route) {
            AddScreen(navController = navController,viewModel = viewModel, coroutineScope = coroutineScope, snackbarHostState = snackbarHostState)
        }
        composable(
            route = Screens.EditScreen.route + "/{name}", arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "-1"
                    nullable = true
                })
        ) { entry ->
            EditScreen(id = entry.arguments?.getString("name"), navController = navController, viewModel = viewModel, coroutineScope = coroutineScope, snackbarHostState = snackbarHostState)
        }
    }
}