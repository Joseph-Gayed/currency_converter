package com.jogayed.currencyconverter.home.rates_list.di

import com.jogayed.core.ICoroutineDispatchers
import com.jogayed.currencyconverter.app_core.room.AppDatabase
import com.jogayed.currencyconverter.home.rates_list.data.local.datasource.CurrencyRatesDao
import com.jogayed.currencyconverter.home.rates_list.data.local.datasource.IRatesLocalDataSource
import com.jogayed.currencyconverter.home.rates_list.data.local.datasource.RatesLocalDataSource
import com.jogayed.currencyconverter.home.rates_list.data.local.mapper.RatesLocalDataMapper
import com.jogayed.currencyconverter.home.rates_list.data.remote.datasource.CurrencyRatesApi
import com.jogayed.currencyconverter.home.rates_list.data.remote.datasource.IRatesRemoteDataSource
import com.jogayed.currencyconverter.home.rates_list.data.remote.datasource.RatesRemoteDataSource
import com.jogayed.currencyconverter.home.rates_list.data.remote.mapper.RatesRemoteDataMapper
import com.jogayed.currencyconverter.home.rates_list.data.repository.CurrencyRatesRepository
import com.jogayed.currencyconverter.home.rates_list.domain.repository.ICurrencyRatesRepository
import com.jogayed.currencyconverter.home.rates_list.domain.usecases.GetRatesListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RatesListModule {
    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): CurrencyRatesApi {
        return retrofit.create(CurrencyRatesApi::class.java)
    }

    @Singleton
    @Provides
    fun providesDao(appDatabase: AppDatabase): CurrencyRatesDao {
        return appDatabase.ratesDao()
    }


    @Singleton
    @Provides
    fun providesRemoteDataSource(
        api: CurrencyRatesApi,
        coroutineDispatcher: ICoroutineDispatchers
    ): IRatesRemoteDataSource {
        return RatesRemoteDataSource(api, coroutineDispatcher)
    }

    @Singleton
    @Provides
    fun providesLocalDataSource(
        dao: CurrencyRatesDao,
        coroutineDispatcher: ICoroutineDispatchers
    ): IRatesLocalDataSource {
        return RatesLocalDataSource(dao, coroutineDispatcher)
    }

    @Singleton
    @Provides
    fun providesRepository(
        localDs: IRatesLocalDataSource,
        remoteDs: IRatesRemoteDataSource,
        localDataMapper: RatesLocalDataMapper,
        remoteDataMapper: RatesRemoteDataMapper
    ): ICurrencyRatesRepository {
        return CurrencyRatesRepository(localDs, remoteDs, localDataMapper, remoteDataMapper)
    }

    @Singleton
    @Provides
    fun providesUseCase(
        repository: ICurrencyRatesRepository
    ): GetRatesListUseCase {
        return GetRatesListUseCase(repository)
    }


}