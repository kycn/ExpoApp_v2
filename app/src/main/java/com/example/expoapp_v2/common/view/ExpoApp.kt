package com.example.expoapp_v2.common.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.expoapp_v2.common.view.theme.ExpoApp_v2Theme

@Composable
fun ExpoApp() {
    ExpoApp_v2Theme {
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier,
        ) { padding ->
            ExpoAppTopNavHost(
                navController = navController,
                modifier = Modifier
                    .padding(padding)
            )
        }
    }
}
