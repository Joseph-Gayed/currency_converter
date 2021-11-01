package com.jogayed.currencyconverter.home.calculator.domain.action

import com.jogayed.core.Action
import com.jogayed.currencyconverter.home.calculator.domain.usecases.CalculationInputParams

sealed class CalculationAction : Action {
    data class CalculateRate(val calculationInputInputs: CalculationInputParams) : CalculationAction()
}