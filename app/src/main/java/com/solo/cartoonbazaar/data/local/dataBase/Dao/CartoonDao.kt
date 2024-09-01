package com.solo.cartoonbazaar.data.local.dataBase.Dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.solo.cartoonbazaar.domain.model.Cartoon
import kotlinx.coroutines.flow.Flow

@Dao
interface CartoonDao {

    @Query("SELECT * FROM Cartoon")
    fun getAllCartoons() : Flow<List<Cartoon>>

    @Query("DELETE FROM Cartoon")
    suspend fun deleteAllCartoons()

    @Upsert
    suspend fun insertCartoon(cartoon: Cartoon)
}