package com.solo.cartoonbazaar.data.remote.CartoonDto

import kotlinx.serialization.Serializable

@Serializable
data class CartoonDto(
    val video: Video
)