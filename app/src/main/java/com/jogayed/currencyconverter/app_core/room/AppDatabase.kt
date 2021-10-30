package com.jogayed.currencyconverter.app_core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jogayed.currencyconverter.app_core.room.AppDatabase.Companion.DATABASE_VERSION


@Database(entities = [], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "currency_converter_db"
        const val DATABASE_VERSION = 1
    }
}