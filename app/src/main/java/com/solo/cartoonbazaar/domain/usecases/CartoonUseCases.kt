package com.solo.cartoonbazaar.domain.usecases


data class CartoonUseCases(
    val startCatchingCartoonsUseCase: StartCatchingCartoonsUseCase,
    val deleteAllCartoonsUseCase: DeleteAllCartoonsUseCase,
    val getCartoonsUseCase: GetCartoonsUseCase,
    val writeCartoonNameUseCase: WriteCartoonNameUseCase,
    val readCartoonNameUseCase: ReadCartoonNameUseCase
)
