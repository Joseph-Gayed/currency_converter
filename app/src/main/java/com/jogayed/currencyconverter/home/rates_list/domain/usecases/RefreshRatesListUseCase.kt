package com.jogayed.currencyconverter.home.rates_list.domain.usecases

import com.jogayed.core.domin.ISuspendableUseCase
import com.jogayed.currencyconverter.home.rates_list.domain.model.CurrencyRateDomainModel
import com.jogayed.currencyconverter.home.rates_list.domain.repository.ICurrencyRatesRepository
import javax.inject.Inject

class RefreshRatesListUseCase @Inject constructor(private val repository: ICurrencyRatesRepository) :
    ISuspendableUseCase.WithoutParams<List<CurrencyRateDomainModel>> {
    override suspend fun invoke(): List<CurrencyRateDomainModel> {
        return repository.getLatestRates(true)
    }
}