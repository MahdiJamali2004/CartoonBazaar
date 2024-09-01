package com.solo.cartoonbazaar.domain.usecases

import com.solo.cartoonbazaar.domain.model.CartoonName
import com.solo.cartoonbazaar.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ReadCartoonNameUseCase @Inject constructor(
    private  val cartoonRepository: CartoonRepository
) {
    operator fun invoke(): Flow<CartoonName?> {
        return cartoonRepository.readCartoonName()
    }
}