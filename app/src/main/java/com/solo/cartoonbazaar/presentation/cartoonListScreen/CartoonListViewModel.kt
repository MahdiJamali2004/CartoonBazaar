package com.solo.cartoonbazaar.presentation.cartoonListScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.solo.cartoonbazaar.data.util.Result
import com.solo.cartoonbazaar.domain.model.Cartoon
import com.solo.cartoonbazaar.domain.model.CartoonName
import com.solo.cartoonbazaar.domain.model.toCartoonDrawableResId
import com.solo.cartoonbazaar.domain.model.toCartoonListUid
import com.solo.cartoonbazaar.domain.model.toPersianName
import com.solo.cartoonbazaar.domain.usecases.CartoonUseCases
import com.solo.cartoonbazaar.domain.util.NetworkCallBack
import com.solo.cartoonbazaar.domain.util.NetworkChecker
import com.solo.cartoonbazaar.navigation.CartoonListScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartoonListViewModel @Inject constructor(
    private val cartoonUseCases: CartoonUseCases,
    private val networkChecker: NetworkChecker,
    private val networkCallBack: NetworkCallBack,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private var _cartoons = MutableStateFlow(emptyList<Cartoon>())
    val cartoons = _cartoons.asStateFlow()

    private var cartoonsUid = MutableStateFlow<List<String>>(emptyList())

    private var _isNetworkConnected = MutableStateFlow(true)
    val isNetworkConnected = _isNetworkConnected.asStateFlow()

    var isLoading = mutableStateOf(true)
        private set


    var networkDialogState by mutableStateOf(false)


    var persianCartoonName = mutableStateOf("")
        private set

    var cartoonPlaceHolder = -1
        private set

    init {
        _isNetworkConnected.value = networkChecker.isInternetAvailable()


        val cartoonNameArgument = savedStateHandle.toRoute<CartoonListScreen>().cartoonName
        cartoonPlaceHolder = cartoonNameArgument.toCartoonDrawableResId()
        persianCartoonName.value = cartoonNameArgument.toPersianName()

        cartoonsUid.value = cartoonNameArgument.toCartoonListUid()

        //check if should clear cache if new cartoon is chosen
        viewModelScope.launch(Dispatchers.IO) {
            cartoonUseCases.readCartoonNameUseCase()
                .collect {
                    if (it != null && it.value == cartoonNameArgument) {
                        cartoonUseCases.deleteAllCartoonsUseCase()
                    } else {
                        cartoonUseCases.writeCartoonNameUseCase(
                            CartoonName.fromValue(cartoonNameArgument)!!
                        )
                    }
                }
        }


        viewModelScope.launch(Dispatchers.IO) {
            networkCallBack.isInternetAvailable().collectLatest { isConnected ->
                _isNetworkConnected.value = isConnected
            }
        }

        viewModelScope.launch {
            isNetworkConnected.collect { isConnect ->
                networkDialogState = isConnect
                if (isConnect) {
                    cartoonsUid.value.forEachIndexed { index, uid ->
                        val result = cartoonUseCases.startCatchingCartoonsUseCase(uid)
                        if (result is Result.Success) {
                            cartoonsUid.value =
                                cartoonsUid.value.toMutableList().apply { remove(uid) }
                        }
                        if (isLoading.value == false) {
                            isLoading.value = true
                        }

                    }
                    isLoading.value = false
                }
            }
        }


        viewModelScope.launch(Dispatchers.IO) {
            cartoonUseCases.getCartoonsUseCase().collect {
                _cartoons.value = it

            }

        }

    }


}