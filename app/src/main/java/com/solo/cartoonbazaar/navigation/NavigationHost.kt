package com.solo.cartoonbazaar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solo.cartoonbazaar.presentation.MainScreen.CartoonPoster
import com.solo.cartoonbazaar.presentation.MainScreen.component.RootMainScreen
import com.solo.cartoonbazaar.presentation.cartoonListScreen.component.RootCartoonListScreen
import com.solo.cartoonbazaar.presentation.playerScreen.PlayerScreen

@Composable
fun NavigationHost(cartoonPosters: List<CartoonPoster>) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreen ) {
        composable<MainScreen>{
            RootMainScreen(navController = navController , cartoonPosters = cartoonPosters)
        }
        composable<CartoonListScreen>{
            RootCartoonListScreen(navController = navController)
        }
        composable<PlayerScreen> {
           PlayerScreen(navController = navController)
        }

    }
}