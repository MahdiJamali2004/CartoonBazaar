package com.solo.cartoonbazaar.navigation

import kotlinx.serialization.Serializable

@Serializable
object MainScreen

@Serializable
data class PlayerScreen(
    val url : String
)

@Serializable
data class CartoonListScreen(
    val cartoonName: Int
)