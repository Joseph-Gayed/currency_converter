package com.jogayed.currencyconverter.home.rates_list.data.local.datasource

import com.jogayed.core.ICoroutineDispatchers
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateWithBaseRateLocalDataModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RatesLocalDataSource @Inject constructor(
    private val ratesDao: CurrencyRatesDao,
    private val coroutineDispatcher: ICoroutineDispatchers
) : IRatesLocalDataSource {
    override suspend fun getLatestRates(baseRate: String): List<CurrencyRateLocalDataModel> {
        return withContext(coroutineDispatcher.io) {
            ratesDao.getLatestRates(baseRate)?.rates?: emptyList()
        }
    }

    override suspend fun saveLatestRates(
        baseRate: String,
        rates: List<CurrencyRateLocalDataModel>
    ) {
        withContext(coroutineDispatcher.io) {
            ratesDao.save(CurrencyRateWithBaseRateLocalDataModel(baseRate,rates))
        }
    }

    override suspend fun deleteAllRates() {
        withContext(coroutineDispatcher.io) {
            ratesDao.deleteAll()
        }
    }
}