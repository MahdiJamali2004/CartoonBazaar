package com.solo.cartoonbazaar.data.util

sealed class Result<T>(val data : T? = null,val exception : Exception? = null ) {
    data class Success<T>(val resultData: T) : Result<T>(resultData)
    data class Error<T>(val e: Exception, val d: T? = null) : Result<T>(d, e)

}