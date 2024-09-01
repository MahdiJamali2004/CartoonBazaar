package com.solo.cartoonbazaar.data.remote.CartoonDto

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val name: String,
    val video_cnt: String
)