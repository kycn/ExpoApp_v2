package com.example.expoapp_v2.characters.view

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.expoapp_v2.characters.domain.model.CharacterItem
import com.example.expoapp_v2.characters.view.composable.Characters
import com.example.expoapp_v2.common.view.ExpoAppNavDestination

object CharactersDestination : ExpoAppNavDestination {
    override val route = "product_overview_route"
    override val destination = "product_overview_destination"
}

fun NavGraphBuilder.charactersScreenGraph(
    navigateToProductDetail: (CharacterItem) -> Unit
) {
    navigation(
        route = CharactersDestination.route,
        startDestination = CharactersDestination.destination
    ) {
        composable(route = CharactersDestination.destination) {
            Characters(
                navigateToProductDetail = navigateToProductDetail
            )
        }
    }
}