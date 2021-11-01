package com.jogayed.currencyconverter.home.rates_list.data.local.datasource

import com.jogayed.core.ICoroutineDispatchers
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RatesLocalDataSource @Inject constructor(
    private val ratesDao: CurrencyRatesDao,
    private val coroutineDispatcher: ICoroutineDispatchers
) : IRatesLocalDataSource {
    override suspend fun getLatestRates(): List<CurrencyRateLocalDataModel> {
        return withContext(coroutineDispatcher.io) {
            ratesDao.getLatestRates()
        }
    }

    override suspend fun saveLatestRates(rates: List<CurrencyRateLocalDataModel>) {
        withContext(coroutineDispatcher.io) {
            ratesDao.saveAll(rates)
        }
    }

    override suspend fun deleteAllRates() {
        withContext(coroutineDispatcher.io) {
            ratesDao.deleteAll()
        }
    }
}