package com.solo.cartoonbazaar.presentation.cartoonListScreen.component


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adivery.sdk.Adivery
import com.solo.cartoonbazaar.navigation.PlayerScreen
import com.solo.cartoonbazaar.presentation.cartoonListScreen.CartoonListViewModel
import com.solo.cartoonbazaar.presentation.util.Constants.ADIVERY_INTERSTITIAL_KEY
import com.solo.cartoonbazaar.presentation.util.Constants.ADIVERY_REWARDED_KEY

@Composable
fun RootCartoonListScreen(
    modifier: Modifier = Modifier,
    viewModel: CartoonListViewModel = hiltViewModel(),
    navController: NavController
) {

//    BackHandler {
//        if (Adivery.isLoaded(ADIVERY_INTERSTITIAL_KEY)){
//
//            Adivery.showAd(ADIVERY_INTERSTITIAL_KEY)
//        }
//        navController.popBackStack()
//
//    }

    CartoonListScreen(
        modifier = modifier,
        cartoons = viewModel.cartoons.collectAsState().value,
        cartoonPlaceHolder = viewModel.cartoonPlaceHolder,
        persianCartoonName = viewModel.persianCartoonName.value,
        isLoading = viewModel.isLoading.value,
        onCartoonClick = { url ->
            if (Adivery.isLoaded(ADIVERY_INTERSTITIAL_KEY)){
                Adivery.showAd(ADIVERY_REWARDED_KEY)
            }

            navController.navigate(PlayerScreen(url))
        },
    )
    if (!viewModel.networkDialogState) {
        NetworkInformationDialog(
            onDismissRequest = {
                viewModel.networkDialogState = true
            }
        )
    }

}

