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
    override suspend fun getLatestRates(
        baseRate: String,
        shouldRefresh: Boolean
    ): List<CurrencyRateDomainModel> {
        val cachedList: List<CurrencyRateLocalDataModel> = localDataSource.getLatestRates(baseRate)

        return if (cachedList.isNotEmpty() && !shouldRefresh) {
            localDataMapper.mapList(cachedList)
        } else {
            val remoteList = remoteDataSource.getLatestRates(baseRate)
            if (remoteList.isNotEmpty()) {
                deleteAllRates()
                saveLatestRates(baseRate, remoteDataMapper.mapList(remoteList))
                remoteDataMapper.mapList(remoteList)
            } else {
                localDataMapper.mapList(cachedList)
            }

        }
    }

    override suspend fun saveLatestRates(baseRate: String, rates: List<CurrencyRateDomainModel>) =
        localDataSource.saveLatestRates(baseRate, localDataMapper.reverseMapList(rates))


    override suspend fun deleteAllRates() =
        localDataSource.deleteAllRates()

}