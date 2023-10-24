package com.jogayed.currencyconverter.app_core.room

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jogayed.currencyconverter.app_core.room.AppDatabase.Companion.DATABASE_VERSION
import com.jogayed.currencyconverter.home.rates_list.data.local.datasource.CurrencyRatesDao
import com.jogayed.currencyconverter.home.rates_list.data.local.datasource.RatesListTypeConverter
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateWithBaseRateLocalDataModel


@Database(entities = [CurrencyRateWithBaseRateLocalDataModel::class], version = DATABASE_VERSION)
@TypeConverters(
    RatesListTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "currency_converter_db"
        const val DATABASE_VERSION = 1
    }

    abstract fun ratesDao(): CurrencyRatesDao
}