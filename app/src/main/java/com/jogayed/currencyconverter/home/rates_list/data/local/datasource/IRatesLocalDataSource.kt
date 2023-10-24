package com.jogayed.currencyconverter.home.rates_list.data.local.datasource

import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel

interface IRatesLocalDataSource {
    suspend fun getLatestRates(baseRate: String): List<CurrencyRateLocalDataModel>
    suspend fun saveLatestRates(baseRate: String,rates: List<CurrencyRateLocalDataModel>)
    suspend fun deleteAllRates()
}