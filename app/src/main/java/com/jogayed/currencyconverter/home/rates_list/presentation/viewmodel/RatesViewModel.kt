package com.jogayed.currencyconverter.home.rates_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jogayed.core.presentation.viewmodel.DataState
import com.jogayed.currencyconverter.app_core.AppConsts
import com.jogayed.currencyconverter.home.rates_list.domain.usecases.GetRatesListUseCase
import com.jogayed.currencyconverter.home.rates_list.domain.usecases.RefreshRatesListUseCase
import com.jogayed.currencyconverter.home.rates_list.presentation.mapper.RatesUiMapper
import com.jogayed.currencyconverter.home.rates_list.presentation.model.CurrencyRateUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(
    private val getRatesListUseCase: GetRatesListUseCase,
    private val refreshRatesListUseCase: RefreshRatesListUseCase,
    private val uiMapper: RatesUiMapper
) : ViewModel() {


    private val _states =
        MutableStateFlow<DataState<List<CurrencyRateUiModel>>>(DataState.Initial)
    val states: StateFlow<DataState<List<CurrencyRateUiModel>>> = _states

    var selectedCurrencyRate: CurrencyRateUiModel? = null
    var baseCurrencyRate: CurrencyRateUiModel? = null

    var nameOfCurrentBaseCurrency: String = AppConsts.BASE_CURRENCY

    fun getRates(shouldRefresh: Boolean) {
        viewModelScope.launch {
            try {
                _states.emit(DataState.Loading(true, cachedData = _states.value.data()))

                val result = if (shouldRefresh)
                    refreshRatesListUseCase(nameOfCurrentBaseCurrency)
                else
                    getRatesListUseCase(nameOfCurrentBaseCurrency)

                val ratesUiList = uiMapper.mapList(result)
                baseCurrencyRate = ratesUiList.find {
                    it.name.equals(nameOfCurrentBaseCurrency, true)
                }
                _states.emit(DataState.Success(ratesUiList))

            } catch (e: Exception) {
                _states.emit(DataState.Error(throwable = e))
            }
        }
    }

    fun refresh() {
        getRates(shouldRefresh = true)
    }

    fun changeBaseRate(newBaseRate:String){
        this.nameOfCurrentBaseCurrency = newBaseRate
        getRates(shouldRefresh = true)
    }

}