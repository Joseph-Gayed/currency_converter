package com.jogayed.currencyconverter.home.rates_list.data.repository

import com.jogayed.currencyconverter.home.rates_list.data.local.datasource.IRatesLocalDataSource
import com.jogayed.currencyconverter.home.rates_list.data.local.mapper.RatesLocalDataMapper
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel
import com.jogayed.currencyconverter.home.rates_list.data.remote.datasource.IRatesRemoteDataSource
import com.jogayed.currencyconverter.home.rates_list.data.remote.mapper.RatesRemoteDataMapper
import com.jogayed.currencyconverter.home.rates_list.data.remote.model.CurrencyRateRemoteDataModel
import com.jogayed.currencyconverter.home.rates_list.domain.model.CurrencyRateDomainModel
import com.jogayed.currencyconverter.home.rates_list.domain.repository.ICurrencyRatesRepository
import javax.inject.Inject

class CurrencyRatesRepository @Inject constructor(
    private val localDataSource: IRatesLocalDataSource,
    private val remoteDataSource: IRatesRemoteDataSource,
    private val localDataMapper: RatesLocalDataMapper,
    private val remoteDataMapper: RatesRemoteDataMapper,
) : ICurrencyRatesRepository {
    override suspend fun getLatestRates(shouldRefresh: Boolean): List<CurrencyRateDomainModel> {
        val cachedList: List<CurrencyRateLocalDataModel> = localDataSource.getLatestRates()
        if (cachedList.isNotEmpty() && !shouldRefresh) {
            return localDataMapper.mapList(cachedList)
        }


        val remoteList: List<CurrencyRateRemoteDataModel> = remoteDataSource.getLatestRates()
        if (remoteList.isNotEmpty()) {
            deleteAllRates()
            saveLatestRates(remoteDataMapper.mapList(remoteList))
        }
        return remoteDataMapper.mapList(remoteList)
    }

    override suspend fun saveLatestRates(rates: List<CurrencyRateDomainModel>) {
        localDataSource.saveLatestRates(localDataMapper.reverseMapList(rates))
    }

    override suspend fun deleteAllRates() {
        localDataSource.deleteAllRates()
    }
}