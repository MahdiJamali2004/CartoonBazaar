package com.solo.cartoonbazaar.presentation.MainScreen

import androidx.annotation.DrawableRes
import com.solo.cartoonbazaar.domain.model.CartoonName
import kotlinx.serialization.Serializable

@Serializable
data class CartoonPoster(
    val drawableRes : Int,
    val cartoonName : CartoonName,
)

