package com.jogayed.currencyconverter.home.rates_list.data.remote.mapper

import com.jogayed.core.mapper.IMapper
import com.jogayed.currencyconverter.home.rates_list.data.remote.model.CurrencyRateRemoteDataModel
import com.jogayed.currencyconverter.home.rates_list.domain.model.CurrencyRateDomainModel
import javax.inject.Inject

/**
 * Map from remote data model to domain model
 */
class RatesRemoteDataMapper @Inject constructor() :
    IMapper<CurrencyRateRemoteDataModel, CurrencyRateDomainModel> {
    override fun map(inputFormat: CurrencyRateRemoteDataModel): CurrencyRateDomainModel {
        return CurrencyRateDomainModel(
            name = inputFormat.name,
            rate = inputFormat.rate
        )
    }

    override fun reverseMap(inputFormat: CurrencyRateDomainModel): CurrencyRateRemoteDataModel {
        return CurrencyRateRemoteDataModel(
            name = inputFormat.name,
            rate = inputFormat.rate
        )
    }

    fun mapList(inputFormat: List<CurrencyRateRemoteDataModel>): List<CurrencyRateDomainModel> {
        return inputFormat.map {
            map(it)
        }
    }

    fun reverseMapList(inputFormat: List<CurrencyRateDomainModel>): List<CurrencyRateRemoteDataModel> {
        return inputFormat.map {
            reverseMap(it)
        }
    }

}