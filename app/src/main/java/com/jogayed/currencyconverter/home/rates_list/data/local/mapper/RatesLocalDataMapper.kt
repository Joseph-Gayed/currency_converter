package com.jogayed.currencyconverter.home.rates_list.data.local.mapper

import com.jogayed.core.mapper.IMapper
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel
import com.jogayed.currencyconverter.home.rates_list.domain.model.CurrencyRateDomainModel
import javax.inject.Inject

/**
 * Map from remote data model to domain model
 */
class RatesLocalDataMapper @Inject constructor() :
    IMapper<CurrencyRateLocalDataModel, CurrencyRateDomainModel> {
    override fun map(inputFormat: CurrencyRateLocalDataModel): CurrencyRateDomainModel {
        return CurrencyRateDomainModel(
            name = inputFormat.name,
            rate = inputFormat.rate
        )
    }

    override fun reverseMap(inputFormat: CurrencyRateDomainModel): CurrencyRateLocalDataModel {
        return CurrencyRateLocalDataModel(
            name = inputFormat.name,
            rate = inputFormat.rate
        )
    }

    fun mapList(inputFormat: List<CurrencyRateLocalDataModel>): List<CurrencyRateDomainModel> {
        return inputFormat.map {
            map(it)
        }
    }

    fun reverseMapList(inputFormat: List<CurrencyRateDomainModel>): List<CurrencyRateLocalDataModel> {
        return inputFormat.map {
            reverseMap(it)
        }
    }

}