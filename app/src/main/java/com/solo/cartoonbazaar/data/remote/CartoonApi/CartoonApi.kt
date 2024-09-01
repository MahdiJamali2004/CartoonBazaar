package com.solo.cartoonbazaar.data.remote.CartoonApi

import com.solo.cartoonbazaar.data.remote.CartoonDto.CartoonDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CartoonApi {

    @GET("video/videohash/{uid}")
    suspend fun getCartoonDto(@Path("uid") uid : String) :CartoonDto

    companion object{
        const val CARTOON_BASE_URL = "https://www.aparat.com/etc/api/"
    }
}