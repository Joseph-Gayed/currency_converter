package com.jogayed.currencyconverter.home.calculator.domain.viewstate

import com.jogayed.core.ViewState

sealed class CalculationViewState : ViewState {

    override fun initialState(): ViewState = Idle

    object Idle : CalculationViewState()
    data class SUCCESS(val value: Double) : CalculationViewState()
    data class Error(val message: String) : CalculationViewState()

}
