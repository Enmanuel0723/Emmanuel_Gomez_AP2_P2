package com.example.enmanuel_gomez_ap2_p2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.enmanuel_gomez_ap2_p2.presentation.entidad.EditScreen
import com.example.enmanuel_gomez_ap2_p2.presentation.entidad.ListScreen
import kotlinx.serialization.Serializable

@Serializable
object ListScreenRoute

@Serializable
data class EditScreenRoute(val entidadId: Int? = null)

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ListScreenRoute
    ) {
        composable<ListScreenRoute> {
            ListScreen(
                onNavigateToEdit = { id ->
                    navController.navigate(EditScreenRoute(entidadId = id))
                }
            )
        }
        composable<EditScreenRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<EditScreenRoute>()
            EditScreen(
                entidadId = route.entidadId,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
