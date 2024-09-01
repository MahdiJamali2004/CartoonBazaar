package com.solo.cartoonbazaar


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.viewinterop.AndroidView
import com.adivery.sdk.Adivery
import com.adivery.sdk.AdiveryBannerAdView
import com.adivery.sdk.BannerSize
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solo.cartoonbazaar.domain.model.CartoonName
import com.solo.cartoonbazaar.navigation.NavigationHost
import com.solo.cartoonbazaar.presentation.MainScreen.CartoonPoster
import com.solo.cartoonbazaar.presentation.ui.theme.BlackBackground
import com.solo.cartoonbazaar.presentation.ui.theme.CartoonBazarTheme
import com.solo.cartoonbazaar.presentation.util.Constants.ADIVERY_OPEN_APP_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val cartoonPosters = listOf(
            CartoonPoster(
                drawableRes = R.drawable.spongbob5,
                cartoonName = CartoonName.SpongeBob
            ),
            CartoonPoster(
                drawableRes = R.drawable.dokhtar_kafshdoozaki,
                cartoonName = CartoonName.DokhtarKafshdoozaki
            ),
            CartoonPoster(
                drawableRes = R.drawable.dragon_riders3,
                cartoonName = CartoonName.DragonRiders
            ),
            CartoonPoster(
                drawableRes = R.drawable.catdog2,
                cartoonName = CartoonName.CatDog
            ),

            CartoonPoster(
                drawableRes = R.drawable.kongfupanda,
                cartoonName = CartoonName.KongFuPanda
            ),
            CartoonPoster(
                drawableRes = R.drawable.ben11,
                cartoonName = CartoonName.BenTen
            ),
            CartoonPoster(
                drawableRes = R.drawable.pink_panther2,
                cartoonName = CartoonName.PinkPanther
            ),
            CartoonPoster(
                drawableRes = R.drawable.turtles3,
                cartoonName = CartoonName.NinjaTurtles
            ),
            CartoonPoster(
                drawableRes = R.drawable.mrbeen3,
                cartoonName = CartoonName.MrBeen
            ),
            CartoonPoster(
                drawableRes = R.drawable.boss_baby,
                cartoonName = CartoonName.BossBaby
            ),
            CartoonPoster(
                drawableRes = R.drawable.lucky_luck2jpg,
                cartoonName = CartoonName.LuckyLook
            ),
            CartoonPoster(
                drawableRes = R.drawable.tom_jerry,
                cartoonName = CartoonName.TomJerry
            ),
            CartoonPoster(
                drawableRes = R.drawable.marsupilami2,
                cartoonName = CartoonName.PalangDomDeraz
            ),

            CartoonPoster(
                drawableRes = R.drawable.asr_hajar,
                cartoonName = CartoonName.AsrHajar
            ),
            CartoonPoster(
                drawableRes = R.drawable.oscar,
                cartoonName = CartoonName.Oscar
            ),


            )
        super.onCreate(savedInstanceState)
//        Adivery.prepareAppOpenAd(this,ADIVERY_OPEN_APP_KEY)

        enableEdgeToEdge()
        setContent {


            LaunchedEffect(Unit) {
                if (Adivery.isLoaded(ADIVERY_OPEN_APP_KEY)){
                    Adivery.showAppOpenAd(this@MainActivity,ADIVERY_OPEN_APP_KEY)
                }
            }
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                CartoonBazarTheme(darkTheme = true) {
                    val systemUiController = rememberSystemUiController()

                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = BlackBackground,
                            darkIcons = false
                        )
                    }
                    NavigationHost(cartoonPosters)
                }
            }
        }
    }


}

@Composable
fun BannerAdView(placementId: String) {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx: Context ->
            AdiveryBannerAdView(ctx).apply {
                setPlacementId(placementId)
                setBannerSize(BannerSize.LARGE_BANNER)
                loadAd()
            }
        }, modifier = Modifier
    )

}