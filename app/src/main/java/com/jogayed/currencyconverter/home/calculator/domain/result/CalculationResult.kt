package com.jogayed.currencyconverter.home.calculator.domain.result

import com.jogayed.core.Result
import com.jogayed.currencyconverter.home.calculator.domain.viewstate.CalculationViewState

sealed class CalculationResult : Result<CalculationViewState> {
    object IDLE : CalculationResult() {
        override fun reduce(
            defaultState: CalculationViewState,
            oldState: CalculationViewState
        ): CalculationViewState {
            return CalculationViewState.Idle
        }
    }

    data class Success(val value: Double) : CalculationResult() {
        override fun reduce(
            defaultState: CalculationViewState,
            oldState: CalculationViewState
        ): CalculationViewState {
            return CalculationViewState.SUCCESS(value)
        }
    }

    data class Error(val message: String) : CalculationResult() {
        override fun reduce(
            defaultState: CalculationViewState,
            oldState: CalculationViewState
        ): CalculationViewState {
            return CalculationViewState.Error(message)
        }
    }

}
