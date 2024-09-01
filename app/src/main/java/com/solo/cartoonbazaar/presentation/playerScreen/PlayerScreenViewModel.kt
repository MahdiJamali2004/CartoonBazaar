package com.solo.cartoonbazaar.presentation.playerScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.solo.cartoonbazaar.domain.util.NetworkCallBack
import com.solo.cartoonbazaar.domain.util.NetworkChecker
import com.solo.cartoonbazaar.navigation.PlayerScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val networkCallBack: NetworkCallBack,
    private val networkChecker: NetworkChecker
) : ViewModel() {



    var networkDialogState by mutableStateOf(false)


    private var _url = MutableStateFlow("")
    val url = _url.asStateFlow()

    init {
        _url.value = savedStateHandle.toRoute<PlayerScreen>().url
        networkDialogState =  networkChecker.isInternetAvailable()

        viewModelScope.launch {
            networkCallBack.isInternetAvailable().collectLatest {
                networkDialogState = it
            }
        }


    }
}