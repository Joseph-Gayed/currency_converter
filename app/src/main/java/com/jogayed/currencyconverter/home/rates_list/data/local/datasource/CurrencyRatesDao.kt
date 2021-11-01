package com.jogayed.currencyconverter.home.rates_list.data.local.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel

@Dao
interface CurrencyRatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(rates: List<CurrencyRateLocalDataModel>)

    @Query("Delete from CurrencyRates")
    suspend fun deleteAll()

    @Query("select * from CurrencyRates")
    suspend fun getLatestRates(): List<CurrencyRateLocalDataModel>
}