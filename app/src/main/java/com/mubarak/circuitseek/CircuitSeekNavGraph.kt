package com.mubarak.circuitseek

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mubarak.circuitseek.ui.HomeScreen
import com.mubarak.circuitseek.ui.WattsCalcScreen

@Composable
fun CircuitSeekNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onDrawerClicked: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Resistance
    ) {
        composable<Resistance> {
            HomeScreen(
                modifier = modifier,
                onDrawer = { onDrawerClicked() }
            )
        }
        composable<Watts> {
            WattsCalcScreen {
                onDrawerClicked()
            }
        }
    }
}
