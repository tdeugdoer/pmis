package com.bsuir.tserashkevich.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val label: String, val icon: ImageVector) {
    object HomeScreen: Screens("HomeScreen", "Home", Icons.Default.Home)
    object FavoriteScreen: Screens("FavoriteScreen", "Favorite", Icons.Default.Favorite)
    object AboutScreen: Screens("AboutScreen", "About", Icons.Default.Info)
    object AddScreen: Screens("AddScreen", "Add film", Icons.Default.Add)
    object EditScreen: Screens("EditScreen", "Edit film", Icons.Default.Edit)

    fun withArgs(vararg args: String) : String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}