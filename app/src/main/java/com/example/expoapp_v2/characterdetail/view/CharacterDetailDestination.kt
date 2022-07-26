package com.example.expoapp_v2.characterdetail.view

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.expoapp_v2.characterdetail.view.composable.CharacterDetail
import com.example.expoapp_v2.common.view.ExpoAppNavDestination

object CharacterDetailDestination : ExpoAppNavDestination {
    override val route = "character_route"
    override val destination = "character_destination"
    const val characterId = "characterId"
}

fun NavGraphBuilder.characterScreenGraph(
    onBackClick: () -> Unit
) {
    composable(
        route = "${CharacterDetailDestination.route}/{${CharacterDetailDestination.characterId}}",
        arguments = listOf(
            navArgument(CharacterDetailDestination.characterId) {
                type = NavType.IntType
            }
        )
    ) {
        CharacterDetail(onBackClick = onBackClick)
    }
}