package com.solo.cartoonbazaar.domain.usecases

import com.solo.cartoonbazaar.data.util.Result
import com.solo.cartoonbazaar.domain.repository.CartoonRepository
import javax.inject.Inject


class StartCatchingCartoonsUseCase @Inject constructor(
    private val cartoonRepository: CartoonRepository
) {
    suspend operator fun invoke(uid: String): Result<Boolean> {
            return cartoonRepository.catchCartoon(uid)
    }
}