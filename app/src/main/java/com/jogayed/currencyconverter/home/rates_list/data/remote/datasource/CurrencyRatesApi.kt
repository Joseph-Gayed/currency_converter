package com.jogayed.currencyconverter.home.rates_list.data.remote.datasource

import com.jogayed.currencyconverter.BuildConfig
import com.jogayed.currencyconverter.home.rates_list.data.remote.model.CurrencyRatesNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRatesApi {
    @GET("latest")
    suspend fun getLatestRates(
        @Query("access_key") apiKey: String = BuildConfig.FIXER_API_KEY,
        @Query("base") base: String = "EUR"
    ): CurrencyRatesNetworkResponse
}