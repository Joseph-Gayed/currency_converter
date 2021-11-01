package com.jogayed.currencyconverter.home.rates_list.presentation.mapper

import com.jogayed.core.mapper.IMapper
import com.jogayed.core.presentation.utils.reFormatDecimalPlaces
import com.jogayed.currencyconverter.home.rates_list.domain.model.CurrencyRateDomainModel
import com.jogayed.currencyconverter.home.rates_list.presentation.model.CurrencyRateUiModel
import javax.inject.Inject

/**
 * Map from remote domain model to Presentation model
 */
class RatesUiMapper @Inject constructor() :
    IMapper<CurrencyRateDomainModel, CurrencyRateUiModel> {
    override fun map(inputFormat: CurrencyRateDomainModel): CurrencyRateUiModel {
        return CurrencyRateUiModel(
            name = inputFormat.name,
            rate = inputFormat.rate.reFormatDecimalPlaces()
        )
    }

    override fun reverseMap(inputFormat: CurrencyRateUiModel): CurrencyRateDomainModel {
        return CurrencyRateDomainModel(
            name = inputFormat.name,
            rate = inputFormat.rate
        )
    }

    fun mapList(inputFormat: List<CurrencyRateDomainModel>): List<CurrencyRateUiModel> {
        return inputFormat.map {
            map(it)
        }
    }


}