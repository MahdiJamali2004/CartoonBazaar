package com.solo.cartoonbazaar

import android.app.Application
import com.adivery.sdk.Adivery
import com.solo.cartoonbazaar.presentation.util.Constants.ADIVERY_APPLICATION_KEY
import com.solo.cartoonbazaar.presentation.util.Constants.ADIVERY_INTERSTITIAL_KEY
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Adivery.configure(this, ADIVERY_APPLICATION_KEY)
        Adivery.setLoggingEnabled(true)
//        Adivery.prepareInterstitialAd(this, ADIVERY_INTERSTITIAL_KEY)

    }
}