package com.jogayed.currencyconverter.home.calculator.domain.usecases

data class CalculationInputParams(
    val baseRate: Double = 1.1,
    val targetRate: Double,
    val inputAmount: Double
)
