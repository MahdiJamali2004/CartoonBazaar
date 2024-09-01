package com.solo.cartoonbazaar.presentation.MainScreen.component

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.solo.cartoonbazaar.BannerAdView
import com.solo.cartoonbazaar.R
import com.solo.cartoonbazaar.presentation.MainScreen.CartoonPoster
import com.solo.cartoonbazaar.presentation.util.Constants.ADIVERY_BANNER_KEY

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    posterList: List<CartoonPoster>,
    onCartoonClick: (Int) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
            .padding(bottom = 16.dp)
    ) {

        LazyColumn(
            modifier = Modifier

                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(posterList) {
                CartoonItem(
                    poster = it,
                    onClick = { onCartoonClick(it.cartoonName.value) }
                )
            }
            item {
                Column(modifier = Modifier
                    .fillMaxWidth() ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        fontWeight = FontWeight.ExtraBold,
                        text = stringResource(R.string.nazarat),
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ), onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://cafebazaar.ir/app/${context.applicationInfo.packageName}")
                        )
                        try {
                            context.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            Log.v("Error", "Activity not found")
                        }
                    }) {
                        Text(
                            fontWeight = FontWeight.ExtraBold,
                            text = "ثبت نظر",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,

                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    BannerAdView(placementId = ADIVERY_BANNER_KEY)
                }

            }

        }
    }
}


