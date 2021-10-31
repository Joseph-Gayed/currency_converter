package com.jogayed.currencyconverter.app_core.room

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.jogayed.currencyconverter.app_core.room.AppDatabase.Companion.DATABASE_VERSION


@Database(entities = [CurrencyRate::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "currency_converter_db"
        const val DATABASE_VERSION = 1
    }
}

@Entity
class CurrencyRate {
    @PrimaryKey
    var id:Int = 0
}
