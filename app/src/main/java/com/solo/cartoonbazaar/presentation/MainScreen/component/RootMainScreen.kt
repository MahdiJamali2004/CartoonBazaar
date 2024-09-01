package com.solo.cartoonbazaar.presentation.MainScreen.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.adivery.sdk.Adivery
import com.solo.cartoonbazaar.navigation.CartoonListScreen
import com.solo.cartoonbazaar.presentation.MainScreen.CartoonPoster
import com.solo.cartoonbazaar.presentation.util.Constants.ADIVERY_INTERSTITIAL_KEY

@Composable
fun RootMainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    cartoonPosters: List<CartoonPoster>
) {

    MainScreen(
        modifier = modifier,
        posterList = cartoonPosters,
        onCartoonClick = { cartoonNameValue ->
            if (Adivery.isLoaded(ADIVERY_INTERSTITIAL_KEY))
                Adivery.showAd(ADIVERY_INTERSTITIAL_KEY)

            navController.navigate(CartoonListScreen(cartoonNameValue))
        }
    )

}

