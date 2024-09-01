package com.solo.cartoonbazaar.domain.usecases

import com.solo.cartoonbazaar.domain.repository.CartoonRepository
import javax.inject.Inject

class DeleteAllCartoonsUseCase  @Inject constructor(
    private val cartoonRepository: CartoonRepository
){

    suspend operator fun invoke() {
        cartoonRepository.deleteAllCartoons()
    }
}