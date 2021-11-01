package com.jogayed.currencyconverter.home.rates_list.data.local.datasource

import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel

interface IRatesLocalDataSource {
    suspend fun getLatestRates(): List<CurrencyRateLocalDataModel>
    suspend fun saveLatestRates(rates: List<CurrencyRateLocalDataModel>)
    suspend fun deleteAllRates()
}