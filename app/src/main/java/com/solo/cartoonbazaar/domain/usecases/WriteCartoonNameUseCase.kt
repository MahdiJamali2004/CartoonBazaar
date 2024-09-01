package com.solo.cartoonbazaar.domain.usecases

import com.solo.cartoonbazaar.domain.model.CartoonName
import com.solo.cartoonbazaar.domain.repository.CartoonRepository
import javax.inject.Inject


class WriteCartoonNameUseCase @Inject constructor(
    private val cartoonRepository: CartoonRepository
) {
    suspend operator fun invoke(cartoonName: CartoonName){
        cartoonRepository.writeCartoonName(cartoonName)
    }

}