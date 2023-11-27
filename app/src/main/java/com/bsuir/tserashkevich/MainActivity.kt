package com.bsuir.tserashkevich

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bsuir.tserashkevich.navigation.Navigation
import com.bsuir.tserashkevich.ui.theme.DarkGrey
import com.bsuir.tserashkevich.ui.theme.White
import androidx.compose.runtime.rememberCoroutineScope
import com.bsuir.tserashkevich.navigation.Screens
import kotlinx.coroutines.CoroutineScope


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val listOfNavItems = listOf(Screens.HomeScreen, Screens.FavoriteScreen, Screens.AboutScreen)

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope: CoroutineScope = rememberCoroutineScope()

            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = DarkGrey,
                    ) {
                        listOfNavItems.forEach { navItem ->
                            NavigationBarItem(
                                selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                                onClick = {
                                    navController.navigate(navItem.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = navItem.icon,
                                        contentDescription = null,
                                        tint = White
                                    )
                                },
                                label = {
                                    Text(text = navItem.label, color = White)
                                }
                            )
                        }
                    }
                }
            ) {
                Navigation(navController = navController, coroutineScope = coroutineScope, snackbarHostState = snackbarHostState)
            }
        }
    }
}