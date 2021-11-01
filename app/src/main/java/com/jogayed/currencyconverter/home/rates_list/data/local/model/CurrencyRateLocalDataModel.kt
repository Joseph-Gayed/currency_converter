package com.jogayed.currencyconverter.home.rates_list.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CurrencyRates")
data class CurrencyRateLocalDataModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val rate: Double
)