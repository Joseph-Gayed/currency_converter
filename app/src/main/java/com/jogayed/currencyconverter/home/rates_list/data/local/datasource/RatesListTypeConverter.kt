package com.jogayed.currencyconverter.home.rates_list.data.local.datasource

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jogayed.currencyconverter.home.rates_list.data.local.model.CurrencyRateLocalDataModel
import java.lang.reflect.Type


class RatesListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<CurrencyRateLocalDataModel> {
        val listType: Type = object : TypeToken<List<CurrencyRateLocalDataModel>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<CurrencyRateLocalDataModel>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}