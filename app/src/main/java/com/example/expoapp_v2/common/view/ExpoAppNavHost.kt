package com.example.expoapp_v2.common.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.expoapp_v2.characters.view.CharactersDestination
import com.example.expoapp_v2.characters.view.charactersScreenGraph

@Composable
fun ExpoAppTopNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = CharactersDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        charactersScreenGraph(
            navigateToProductDetail = {  }
        )
    }
}