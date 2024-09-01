package com.solo.cartoonbazaar.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import coil.network.HttpException
import com.solo.cartoonbazaar.data.local.dataBase.DataBase
import com.solo.cartoonbazaar.data.remote.CartoonApi.CartoonApi
import com.solo.cartoonbazaar.data.remote.CartoonMapper.toCartoon
import com.solo.cartoonbazaar.data.util.Result
import com.solo.cartoonbazaar.domain.model.Cartoon
import com.solo.cartoonbazaar.domain.model.CartoonName
import com.solo.cartoonbazaar.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

class CartoonRepositoryImpl @Inject constructor(
    private val cartoonApi: CartoonApi,
    private val dataBase: DataBase,
    private val dataStore: DataStore<Preferences>
) :CartoonRepository {
    override suspend fun deleteAllCartoons() {
        dataBase.cartoonDao.deleteAllCartoons()
    }

    override suspend fun getAllCartoons(): Flow<List<Cartoon>> {
        return dataBase.cartoonDao.getAllCartoons()
    }

    override suspend fun catchCartoon(uid: String): Result<Boolean> {
        return try {
            val cartoon = cartoonApi.getCartoonDto(uid).toCartoon()
            dataBase.cartoonDao.insertCartoon(cartoon)
            Result.Success(true)
        }catch(e:IOException){
            Log.v("Error","IoException : $e")
            Result.Error(e,false)
        }catch (e : HttpException){
            Log.v("Error","HttpException: $e")
            Result.Error(e,false)
        }catch (e : Exception){
            Log.v("Error","Exception: $e")
            Result.Error(e,false)
        }
    }


    //dataStoreOperations
    override suspend fun writeCartoonName(cartoonName: CartoonName) {
        dataStore.edit {
            val key = intPreferencesKey(CARTOON_NAME_KEY)
            it[key] = cartoonName.value
        }
    }

    override fun readCartoonName(): Flow<CartoonName?> {
        return dataStore.data.map {
            CartoonName.fromValue(it[intPreferencesKey(CARTOON_NAME_KEY)])
        }
    }
    companion object{
        const val CARTOON_NAME_KEY = "cartoon_name_key"
    }
}