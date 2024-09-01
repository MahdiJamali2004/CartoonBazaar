package com.solo.cartoonbazaar.domain.repository

import com.solo.cartoonbazaar.data.util.Result
import com.solo.cartoonbazaar.domain.model.Cartoon
import com.solo.cartoonbazaar.domain.model.CartoonName
import kotlinx.coroutines.flow.Flow

interface CartoonRepository  {

    suspend fun deleteAllCartoons()
    suspend fun catchCartoon(uid : String) :Result<Boolean>
    suspend fun getAllCartoons() : Flow<List<Cartoon>>

    suspend fun writeCartoonName(cartoonName: CartoonName)
    fun readCartoonName() :Flow<CartoonName?>
}