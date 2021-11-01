package com.jogayed.currencyconverter.home.rates_list.domain.repository

import com.jogayed.currencyconverter.home.rates_list.domain.model.CurrencyRateDomainModel

interface ICurrencyRatesRepository {
    suspend fun getLatestRates(shouldRefresh: Boolean = false): List<CurrencyRateDomainModel>
    suspend fun saveLatestRates(rates: List<CurrencyRateDomainModel>)
    suspend fun deleteAllRates()
}