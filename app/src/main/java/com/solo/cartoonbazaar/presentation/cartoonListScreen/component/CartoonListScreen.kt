package com.solo.cartoonbazaar.presentation.cartoonListScreen.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solo.cartoonbazaar.BannerAdView
import com.solo.cartoonbazaar.domain.model.Cartoon
import com.solo.cartoonbazaar.presentation.util.Constants.ADIVERY_BANNER_KEY

@Composable
fun CartoonListScreen(
    modifier: Modifier = Modifier,
    cartoons: List<Cartoon>,
    isLoading: Boolean,
    persianCartoonName: String,
    @DrawableRes cartoonPlaceHolder: Int,
    onCartoonClick: (url: String) -> Unit,
) {

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            itemsIndexed(cartoons) { index, cartoon ->
                CartoonItemList(
                    cartoon = cartoon,
                    cartoonPlaceHolder = cartoonPlaceHolder,
                    persianCartoonName = persianCartoonName,
                    index = index + 1,
                    onClick = {
                        onCartoonClick(cartoon.url)
                    }

                )
//                if ((index + 1) % 10 == 0) {
//                    BannerAdView(placementId = ADIVERY_BANNER_KEY)
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                }
                HorizontalDivider()
            }
            item {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }


        }
    }
}
