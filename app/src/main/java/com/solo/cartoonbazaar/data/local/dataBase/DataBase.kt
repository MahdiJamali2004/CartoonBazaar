package com.solo.cartoonbazaar.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solo.cartoonbazaar.data.local.dataBase.Dao.CartoonDao
import com.solo.cartoonbazaar.domain.model.Cartoon


@Database(
    entities = [
        Cartoon::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract val cartoonDao : CartoonDao

    companion object{
        const val DATA_BASE_NAME = "cartoon.db"
    }
}