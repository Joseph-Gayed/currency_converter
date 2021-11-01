package com.jogayed.currencyconverter.home.calculator.domain.usecases

import com.jogayed.currencyconverter.home.calculator.domain.result.CalculationResult
import javax.inject.Inject

class CalculateUseCase @Inject constructor() {
    operator fun invoke(input: CalculationInputParams): CalculationResult {
        return try {
            val output = (input.targetRate * input.inputAmount) / input.baseRate
            CalculationResult.Success(output)
        } catch (e: Throwable) {
            CalculationResult.Error(e.message ?: "Something went wrong with the calculation")
        }
    }
}