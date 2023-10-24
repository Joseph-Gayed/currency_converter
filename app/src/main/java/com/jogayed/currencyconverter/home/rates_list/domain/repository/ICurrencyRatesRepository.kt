package com.jogayed.currencyconverter.home.rates_list.domain.repository

import com.jogayed.currencyconverter.home.rates_list.domain.model.CurrencyRateDomainModel

interface ICurrencyRatesRepository {
    suspend fun getLatestRates(baseRate:String,shouldRefresh: Boolean = false): List<CurrencyRateDomainModel>
    suspend fun saveLatestRates(baseRate: String,rates: List<CurrencyRateDomainModel>)
    suspend fun deleteAllRates()
}