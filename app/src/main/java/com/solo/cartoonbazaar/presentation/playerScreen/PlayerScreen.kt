package com.solo.cartoonbazaar.presentation.playerScreen

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.adivery.sdk.Adivery
import com.solo.cartoonbazaar.presentation.cartoonListScreen.component.NetworkInformationDialog
import com.solo.cartoonbazaar.presentation.util.Constants.ADIVERY_INTERSTITIAL_KEY

@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    viewModel: PlayerScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val url = viewModel.url.collectAsState().value

    BackHandler {
        navController.navigateUp()
        if (Adivery.isLoaded(ADIVERY_INTERSTITIAL_KEY)) {
            Adivery.showAd(ADIVERY_INTERSTITIAL_KEY)
        }
    }


    ExoPlayerView(
        url = url, modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    )


    if (!viewModel.networkDialogState)
        NetworkInformationDialog(
            onDismissRequest = { viewModel.networkDialogState = true }
        )

}

@OptIn(UnstableApi::class)
@Composable
fun ExoPlayerView(
    modifier: Modifier = Modifier,
    url: String
) {

    val context = LocalContext.current
    var playbackPosition by rememberSaveable { mutableLongStateOf(0L) }
    var playWhenReady by rememberSaveable { mutableStateOf(false) }
    val exoPlayer = remember {


        ExoPlayer.Builder(context)
            .build().apply {
                val mediaItem = MediaItem.fromUri(Uri.parse(url))
                setMediaItem(mediaItem)
                prepare()
            }
    }
    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            }
        },
        modifier = modifier
            .fillMaxSize()


    )
    DisposableEffect(Unit) {
        exoPlayer.seekTo(playbackPosition)
        exoPlayer.playWhenReady = playWhenReady
        onDispose {
            playbackPosition = exoPlayer.currentPosition
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
    }
}
