package com.solo.cartoonbazaar.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.solo.cartoonbazaar.data.local.dataBase.DataBase
import com.solo.cartoonbazaar.data.remote.CartoonApi.CartoonApi
import com.solo.cartoonbazaar.data.remote.CartoonApi.CartoonApi.Companion.CARTOON_BASE_URL
import com.solo.cartoonbazaar.data.repository.CartoonRepositoryImpl
import com.solo.cartoonbazaar.domain.repository.CartoonRepository
import com.solo.cartoonbazaar.domain.usecases.CartoonUseCases
import com.solo.cartoonbazaar.domain.usecases.DeleteAllCartoonsUseCase
import com.solo.cartoonbazaar.domain.usecases.GetCartoonsUseCase
import com.solo.cartoonbazaar.domain.usecases.ReadCartoonNameUseCase
import com.solo.cartoonbazaar.domain.usecases.StartCatchingCartoonsUseCase
import com.solo.cartoonbazaar.domain.usecases.WriteCartoonNameUseCase
import com.solo.cartoonbazaar.domain.util.NetworkCallBack
import com.solo.cartoonbazaar.domain.util.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    @Provides
    @Singleton
    fun provideCartoonApi(): CartoonApi {
        return Retrofit.Builder()
            .baseUrl(CARTOON_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartoonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) : DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            DataBase.DATA_BASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCartoonRepository(cartoonApi: CartoonApi, dataBase: DataBase,dataStore: DataStore<Preferences>) :CartoonRepository {
        return CartoonRepositoryImpl(cartoonApi,dataBase,dataStore)
    }

    @Provides
    @Singleton
    fun provideGetCartoonUseCase( cartoonRepository: CartoonRepository): GetCartoonsUseCase {
        return GetCartoonsUseCase(cartoonRepository)
    }

    @Provides
    @Singleton
    fun provideCartoonUseCases(cartoonRepository: CartoonRepository): CartoonUseCases {
        return CartoonUseCases(
            startCatchingCartoonsUseCase = StartCatchingCartoonsUseCase(cartoonRepository),
            deleteAllCartoonsUseCase = DeleteAllCartoonsUseCase(cartoonRepository),
            getCartoonsUseCase = GetCartoonsUseCase(cartoonRepository),
            writeCartoonNameUseCase = WriteCartoonNameUseCase(cartoonRepository),
            readCartoonNameUseCase = ReadCartoonNameUseCase(cartoonRepository)

        )
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideNetworkChecker(@ApplicationContext context: Context) : NetworkChecker {
        return NetworkChecker(context)
    }
    @Provides
    @Singleton
    fun provideNetworkCallBack(@ApplicationContext context: Context) : NetworkCallBack {
        return NetworkCallBack(context)
    }

}