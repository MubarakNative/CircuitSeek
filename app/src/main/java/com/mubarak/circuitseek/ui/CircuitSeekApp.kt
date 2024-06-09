package com.mubarak.circuitseek.ui

import android.widget.Toast
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mubarak.circuitseek.CircuitSeekNavGraph
import com.mubarak.circuitseek.Resistance
import com.mubarak.circuitseek.Watts
import kotlinx.coroutines.launch

@Composable
fun CircuitSeekApp() {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: Resistance

    ModalNavigationDrawer(
        drawerContent = {
            CircuitSeekAppDrawer(
                currentDestination = currentRoute,
                navigateToResistance = {
                    navController.navigate(Resistance) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true

                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToWatts = {
                    navController.navigate(Watts) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                closeDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                },
            )
        },

        drawerState = drawerState
    ) { // container for the app content
        CircuitSeekNavGraph(navController = navController) {
            scope.launch {
                drawerState.open()
            }
        }
    }
}