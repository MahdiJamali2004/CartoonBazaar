package com.solo.cartoonbazaar.data.remote.CartoonDto

import kotlinx.serialization.Serializable

@Serializable
data class FileLinkAll(
    val profile: String,
    val text: String,
    val urls: List<String>
)