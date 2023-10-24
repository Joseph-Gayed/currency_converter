package com.jogayed.currencyconverter.home.rates_list.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jogayed.currencyconverter.home.rates_list.data.local.datasource.RatesListTypeConverter

@Entity(tableName = "CurrencyRates")
@TypeConverters(
    RatesListTypeConverter::class
)
data class CurrencyRateWithBaseRateLocalDataModel(
    @PrimaryKey
    @ColumnInfo(name = "baseRate")
    val baseRate: String = "",

    @ColumnInfo(name = "rates")
    val rates: List<CurrencyRateLocalDataModel> = emptyList()
)
data class CurrencyRateLocalDataModel(
    val name: String,
    val rate: Double
)