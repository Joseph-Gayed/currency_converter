package com.jogayed.currencyconverter.home.rates_list.data.remote.model

data class CurrencyRatesNetworkResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)