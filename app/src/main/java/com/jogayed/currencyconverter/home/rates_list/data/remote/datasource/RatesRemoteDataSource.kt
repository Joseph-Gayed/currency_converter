package com.jogayed.currencyconverter.home.rates_list.data.remote.datasource

import com.jogayed.core.ICoroutineDispatchers
import com.jogayed.currencyconverter.app_core.ext.asMap
import com.jogayed.currencyconverter.home.rates_list.data.remote.model.CurrencyRateRemoteDataModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RatesRemoteDataSource @Inject constructor(
    private val currencyRatesApi: CurrencyRatesApi,
    private val coroutineDispatcher: ICoroutineDispatchers
) : IRatesRemoteDataSource {
    override suspend fun getLatestRates(): List<CurrencyRateRemoteDataModel> {
        return withContext(coroutineDispatcher.io) {
            val response = currencyRatesApi.getLatestRates()
            response.rates.asMap().map {
                CurrencyRateRemoteDataModel(it.key, it.value as Double)
            }
        }
    }
}