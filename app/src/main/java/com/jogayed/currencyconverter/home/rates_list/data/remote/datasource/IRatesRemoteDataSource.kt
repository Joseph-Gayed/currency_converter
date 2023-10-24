package com.jogayed.currencyconverter.home.rates_list.data.remote.datasource

import com.jogayed.currencyconverter.home.rates_list.data.remote.model.CurrencyRateRemoteDataModel

interface IRatesRemoteDataSource {
    suspend fun getLatestRates(baseRate:String): List<CurrencyRateRemoteDataModel>
}