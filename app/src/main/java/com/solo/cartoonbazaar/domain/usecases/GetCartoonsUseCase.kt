package com.solo.cartoonbazaar.domain.usecases

import com.solo.cartoonbazaar.domain.model.Cartoon
import com.solo.cartoonbazaar.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCartoonsUseCase @Inject constructor(
    private val cartoonRepository: CartoonRepository
) {
    suspend operator fun invoke(): Flow<List<Cartoon>> {
        return cartoonRepository.getAllCartoons()
    }
}