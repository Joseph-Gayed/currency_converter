package com.jogayed.currencyconverter.home.rates_list.data.local.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateWithBaseRateLocalDataModel

@Dao
interface CurrencyRatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(rate: CurrencyRateWithBaseRateLocalDataModel)

    @Query("Delete from CurrencyRates")
    suspend fun deleteAll()

    @Query("select * from CurrencyRates where baseRate = :baseRate")
    suspend fun getLatestRates(baseRate: String): CurrencyRateWithBaseRateLocalDataModel?
}